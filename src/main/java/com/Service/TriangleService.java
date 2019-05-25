package com.Service;

import com.Counter.CounterService;
import com.Entity.Parameters;
import com.Entity.Cache;
import com.Entity.Statistics;
import com.Entity.Triangle;
import com.Repository.CacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

@Service
public class TriangleService {

    private static final Logger log = Logger.getLogger(TriangleService.class);
    @Autowired
    private com.Cache.CacheMap cacheMap;
    @Autowired
    private CounterService counter;

    @Autowired
    private CounterService counterService;

    @Autowired
    private CacheRepository cacheRepository;

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    @PostConstruct
    void init() {
        cacheRepository.findAll().forEach(value -> {
            Parameters parameters = new Parameters(value.getFirstSide(), value.getSecondSide(), value.getThirdSide());
            Triangle triangle = new Triangle(value.getSquare(), value.getPerimeter());

            cacheMap.put(parameters, triangle);
            log.info("Add " + triangle.toString() + "at cache!");
        });
    }

    private List<Triangle> triangle;

    public synchronized Triangle solveEquetion(String firstSide, String secondSide, String thirdSide) throws NumberFormatException {

        Double fSide = Double.parseDouble(firstSide);
        Double sSide = Double.parseDouble(secondSide);
        Double tSide = Double.parseDouble(thirdSide);

        counter.increment();
        log.info("This service was used " + counter.getCounter());


        Parameters params = new Parameters(firstSide, secondSide, thirdSide);
        if (cacheMap.containKey(params)) {
            log.info("We use cache!!!");
            return cacheMap.get(params);
        }


        if (fSide + sSide > tSide &&
                fSide + tSide > sSide &&
                sSide + tSide > fSide) {
            Double perimeter = fSide + sSide + tSide;
            Double semiPerimeter = perimeter / 2;
            Double square = Math.sqrt(semiPerimeter * (semiPerimeter - fSide) *
                    (semiPerimeter - sSide) * (semiPerimeter - tSide));
            Triangle triangle = new Triangle(square, perimeter);
            cacheMap.put(params, triangle);
            log.info("Loading item into cache!!!");

            return triangle;
        } else {
            return null;
        }
    }


    public Future<List<Triangle>> processList(List<Parameters> list, int id) {
        return this.executor.submit(() -> {
            return list.stream().filter(value -> {

                Double firstSide = value.dGetFirstSide();
                Double secondSide = value.dGetSecondSide();
                Double thirdSide = value.dGetThirdSide();

                boolean isValidSides = firstSide > 0 && secondSide > 0 && thirdSide > 0;
                boolean isExist = firstSide + secondSide > thirdSide &&
                        firstSide + thirdSide > secondSide &&
                        secondSide + thirdSide > firstSide;

                return isValidSides && isExist;
            }).map(value -> {
                System.out.println("huhuuhuh");
                log.info("We are in stream map!");
                if (this.cacheMap.containKey(value)) {
                    log.info("We use cache!!!");
                    return this.cacheMap.get(value);
                }
                Cache result = new Cache();

                Double perimeter = value.dGetFirstSide() +
                        value.dGetSecondSide() +
                        value.dGetThirdSide();
                Double semiPerimeter = perimeter / 2;

                Double square = Math.sqrt(semiPerimeter *
                        (semiPerimeter - value.dGetFirstSide()) *
                        (semiPerimeter - value.dGetSecondSide()) *
                        (semiPerimeter - value.dGetThirdSide()));

                Triangle triangle_result = new Triangle(square, perimeter);

                result.setFirstSide(value.getFirstSide());
                result.setSecondSide(value.getSecondSide());
                result.setThirdSide(value.getThirdSide());
                result.setSquare(triangle_result.getSquare());
                result.setPerimeter(triangle_result.getPerimeter());
                result.setResponceId(id);

                cacheRepository.save(result);
                this.cacheMap.put(new Parameters(value.getFirstSide(), value.getSecondSide(), value.getThirdSide()), triangle_result);
                log.info("HTTP status 200, response :" + triangle_result.toString());
                return triangle_result;
            }).collect(Collectors.toList());
        });
    }

    public Statistics collectStats(List<Parameters> requests) {
        Statistics stats = new Statistics();

        stats.setInputsAmount(requests.size());
        stats.setInvalidInputs(requests.size() - this.triangle.size());
        stats.setMinValue(triangle.stream().min(Comparator.comparingDouble(value -> value.getSquare())).get());
        stats.setMaxValue(triangle.stream().max(Comparator.comparingDouble(value -> value.getSquare())).get());
        stats.setPopularResult(this.findPopularResult());

        return stats;
    }

    public Triangle findPopularResult() {
        if (this.triangle.isEmpty()) return null;

        HashMap<Triangle, Integer> popularityMap = new HashMap<>();
        this.triangle.forEach(a -> {
            if (popularityMap.containsKey(a)) {
                popularityMap.put(a, popularityMap.get(a) + 1);
            } else popularityMap.put(a, 1);
        });

        Triangle popularTriangle = popularityMap.entrySet()
                .stream()
                .max(Comparator.comparingInt(value ->
                        value.getValue()
                )).get().getKey();

        return popularTriangle;
    }

    public List<Cache> getAll() {
        return cacheRepository.findAll();
    }


    public Integer asynchCalculate(List<Parameters> parameters) {
        int id = new Random().nextInt(50000) + 1;

        Future<List<Triangle>> result = processList(parameters, id);

        //logger.info("Return id!");
        return id;
    }


    public List<Triangle> getAnswerById(String id) {
        return cacheRepository.getAllByResponceId(Integer.parseInt(id))
                .stream()
                .map(value ->
                        new Triangle(value.getSquare(), value.getPerimeter()))
                .collect(Collectors.toList());
    }

}



