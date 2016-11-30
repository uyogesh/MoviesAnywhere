package com.androidtutorialpoint.FreeMovies;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Jsoup_parser {

    private String[] movie_name;
    private String[] movie_link;
    private String[] img_link;

    private String[] episodes;
    private String[] Episode_req;
    Document doc;

    public Jsoup_parser(Document document,int level) {
    doc=document;
        if (level==0)
        makeString();

        else if(level==1)
            makeStringForEpisode(document);

    }

    private void makeStringForEpisode(Document docu) {

        Element element=docu.getElementById("server-8");
        Elements elements=element.getElementsByClass("les-content");
        Element div=elements.first();
        Elements child=div.children();
        int len=child.indexOf(child.last());
        episodes=new String[len+1];
        Episode_req=new String[len+1];
        int length=0;
        for(Element a:elements)
        {
            Elements links=a.children();
            for(Element link:links) {

                    episodes[length] = link.attr("title");
                Episode_req[length] = link.attr("onclick");
                length++;
            }
        }
    }


    public void makeString()
    {
        Elements elements = doc.select(".ml-item");
        int len=elements.indexOf(elements.last());
        movie_link=new String[len+1];
        movie_name=new String[len+1];
        int a=0;
        for (Element e : elements) {

            Elements inner = e.children();
            for (Element i : inner) {
                movie_link [a]=i.attr("href");
                movie_name [a] = i.attr("title");

            }

            a++;
        }


    }

    public String[] getEpisode_req() {
        return Episode_req;
    }

    public String[] getMovie_name() {
        return movie_name;
    }

    public String[] getMovie_link() {
        return movie_link;
    }

    public String[] getImg_link() {
        return img_link;
    }

    public String[] getEpisodes() {
        return episodes;
    }
}
