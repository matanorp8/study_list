package com.example.matan.firtst_proyecton;

import java.io.Serializable;
import java.util.Comparator;

public class Source implements Serializable { // An object of Source item
    private String sourcename;
    private int year;
    private String writenBy;
    private String language;
    private String link;
    public Source(String sourcename,int year,String writenBy,String language,String link)
    {
        this.sourcename=sourcename;
        this.year=year;
        this.writenBy=writenBy;
        this.language=language;
        this.link=link;
    }
    public String getSourceName()
    {
        return sourcename;
    }
    public int getYear()
    {
        return year;
    }
    public String getWritenBy()
    {
        return writenBy;
    }
    public String getLanguage()
    {
        return language;
    }
    public String getLink()
    {
        return link;
    }
    public void setSourcename(String sourcename)
    {
        this.sourcename=sourcename;
    }
    public void setYear(int year)
    {
        this.year=year;
    }
    public void setWritenBy(String writenBy)
    {
        this.writenBy=writenBy;
    }
    public void setLanguage(String language)
    {
        this.language=language;
    }
    public void setLink(String link)
    {
        this.link=link;
    }


    public static Comparator<Source> NameComp = new Comparator<Source>() {
        @Override
        public int compare(Source S1, Source S2) {
            String title1 = S1.getSourceName().toLowerCase();
            String title2 = S2.getSourceName().toLowerCase();
            return title1.compareTo(title2);
        }
    };

    public static Comparator<Source> WriterComp = new Comparator<Source>() {
        @Override
        public int compare(Source S1, Source S2) {
            String title1 = S1.getWritenBy().toLowerCase();
            String title2 = S2.getWritenBy().toLowerCase();
            return title1.compareTo(title2);
        }
    };
    public static Comparator<Source> LanguageComp = new Comparator<Source>() {
        @Override
        public int compare(Source S1, Source S2) {
            String title1 = S1.getLanguage().toLowerCase();
            String title2 = S2.getLanguage().toLowerCase();
            return title1.compareTo(title2);
        }
    };


    public static Comparator<Source> YearComp = new Comparator<Source>() {
        @Override
        public int compare(Source s1, Source s2) {
            int year1 = s1.getYear();
            int year2 = s2.getYear();
            return year2 - year1;
        }
    };
}
