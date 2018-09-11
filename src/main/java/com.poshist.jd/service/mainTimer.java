package com.poshist.jd.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
@EnableScheduling
public class mainTimer {

    /**
     * 主进程启动
     */
    @Scheduled(initialDelay  = 5,fixedDelay=86400000)
    private void mainThead() throws IOException {
        String baseUrl1="https://list.jd.com/list.html?cat=737,794,878&page=";
        String baseUrl2="&sort=sort_rank_asc&trans=1&JL=6_0_0#J_main";
        for (int i=1;i<2;i++){
            Document listDoc = Jsoup.connect(baseUrl1+i+baseUrl2).get();
            Elements proInfos= listDoc.getElementsByClass("gl-item");
            for(Element proinfo:proInfos){
                Element urlE=  proinfo.getElementsByClass("p-img").get(0);
              String url=  urlE.child(0).attributes().get("href");
              url="https:"+url;
                Element nameE=  proinfo.getElementsByClass("p-name").get(0);
              String  name=  nameE.child(0).child(0).text();
//              Element priceE=proinfo.getElementsByClass("p-price").get(0);
//                Double price=0d;
//              if(!StringUtils.isEmpty(priceE.child(0).child(1).text())) {
//                   price = Double.parseDouble(priceE.child(0).child(1).text());
//              }

                Document info=Jsoup.connect(url).get();
                Elements elements = info.select("span:tagname(bt)");

                String size =info.getElementsByClass("Ptable-item").get(2).child(1).child(1).text();
                System.out.println(url);
              System.out.println(name);
             System.out.println(size);
            }
        }



    }
}
