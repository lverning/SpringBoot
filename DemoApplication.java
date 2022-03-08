package com.example.demo;

import com.example.demo.converter.MoneyReadConverter;
import com.example.demo.model.Coffee;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Query.query;

@SpringBootApplication
@Slf4j
public class DemoApplication implements ApplicationRunner {

    @Autowired
    private MongoTemplate mongoTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

    }

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(Arrays.asList(new MoneyReadConverter()));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Coffee espresso = Coffee.builder().name("特浓咖啡")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.99))
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        Coffee save = mongoTemplate.save(espresso);
        log.info("咖啡{}", save);

        // 根据名字查询
        List<Coffee> coffeeList = mongoTemplate.find(query(Criteria.where("name").is("特浓咖啡")), Coffee.class);
        log.info("查询到的咖啡条数 {}", coffeeList.size());
        coffeeList.forEach(c -> log.info("咖啡具体信息 {} ", c));

        //查询所有
        List<Coffee> coffeeAll = mongoTemplate.findAll(Coffee.class);
        coffeeAll.forEach(c -> log.info("所有咖啡 {}", c));

        //修改
        Thread.sleep(3000);
        UpdateResult updateResult = mongoTemplate.updateFirst(query(Criteria.where("name").is("特浓咖啡")),
                new Update().set("price", Money.ofMajor(CurrencyUnit.of("CNY"),
                        50)).currentDate("updateTime"), Coffee.class);

        log.info("是否更新成功 {}" , updateResult.getModifiedCount()  );

        Coffee mongoTemplateById = mongoTemplate.findById(save.getId(), Coffee.class);

        log.info("更新之后的咖啡信息{}" , mongoTemplateById);

        //删除
        mongoTemplate.remove(mongoTemplateById);
    }
}
