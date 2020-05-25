package com.song.ocr.scraper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.song.ocr.domain.Course;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class CourseraScraper {

    private static final String baseUrl = "https://www.coursera.org/search?query=";

    public static void main(String [] args) {
        WebClient client = new WebClient();

        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        client.getOptions().setUseInsecureSSL(true);


        String searchQuery = "java";

        try {
            String searchUrl =  baseUrl + URLEncoder.encode(searchQuery, "UTF-8");
            HtmlPage page = client.getPage(searchUrl);
            System.out.println(page.asXml());

            List<Course> courses = new ArrayList<>();

            List<HtmlElement> items = page.getByXPath("//li[@class='ais-InfiniteHits-item']");

            if(items.isEmpty()) {
                System.out.println("No items found !");
            } else {
                items.forEach(item -> {
                    HtmlElement title = item.getFirstByXPath(".//h2[contains(@class, 'card-title')]");
                    HtmlAnchor url = item.getFirstByXPath(".//a");
                    HtmlElement score = item.getFirstByXPath(".//span[@class='ratings-text']");
                    HtmlElement enrollment = item.getFirstByXPath(".//span[@class='enrollment-number']");
                    HtmlElement level = item.getFirstByXPath(".//div[@class='product-difficulty']/span[@class='difficulty']");

                    if(title != null) {
                        Course course = new Course();
                        course.setCourseName(title.asText());
                        course.setDescription("");
                        course.setUrl(url.getHrefAttribute());
                        course.setSkill("Java");
                        course.setScore(Double.parseDouble(score.asText()));
                        course.setEnrollmentNum(enrollment.asText());
                        course.setLevel(level.asText());
                        course.setSource("Coursera");
                        courses.add(course);
                    }
                });
            }

            Settings settings = Settings.builder()
                    .put("cluster.name", "docker-cluster")
                    .build();

            TransportClient elasticSearchClient = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("0.0.0.0"), 9300));

            ObjectMapper mapper = new ObjectMapper();


            courses.forEach(course -> {
                byte[] json = new byte[0];
                try {
                    json = mapper.writeValueAsBytes(course);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }

                IndexResponse response = elasticSearchClient.prepareIndex("course", "_doc")
                        .setSource(json, XContentType.JSON)
                        .get();

                System.out.println(response);
            });

            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
