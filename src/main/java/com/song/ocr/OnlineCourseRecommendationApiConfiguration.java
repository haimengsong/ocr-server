package com.song.ocr;

import com.song.ocr.elasticsearch.SearchService;
import com.song.ocr.elasticsearch.impl.SearchServiceImpl;
import com.song.ocr.extractor.JobDescriptionExtractor;
import com.song.ocr.service.FindCourseService;
import com.song.ocr.service.Impl.FindCourseServiceImpl;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

import java.net.UnknownHostException;

@Configuration
public class OnlineCourseRecommendationApiConfiguration {

    @Bean
    public FindCourseService findCourseService(){
        return new FindCourseServiceImpl();
    }

    @Bean
    public JobDescriptionExtractor jobDescriptionExtractor(){
        return new JobDescriptionExtractor();
    }

    @Bean
    public RestHighLevelClient elasticSearchclient() throws UnknownHostException {
//        Settings settings = Settings.builder()
//                .put("cluster.name", "docker-cluster")
//                .build();
//
//        Client client = new PreBuiltTransportClient(settings)
//                .addTransportAddress(new TransportAddress(InetAddress.getByName("0.0.0.0"), 9300));

        //return client;

        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

        return client;
    }

    @Bean
    public Jedis redisClient() {
        return new Jedis("0.0.0.0", 6379);
    }

    @Bean
    public SearchService searchService() {
        return new SearchServiceImpl();
    }
}
