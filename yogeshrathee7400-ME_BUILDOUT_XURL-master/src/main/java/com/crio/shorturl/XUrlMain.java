package com.crio.shorturl;

public class XUrlMain {

  public static void main(String[] args) {

    XUrl xUrl = new XUrlImpl();

    
    String url = xUrl.registerNewUrl("http://abc.com");
    String url1 = xUrl.registerNewUrl("http://abc1.com");
    String url2 = xUrl.registerNewUrl("http://abc2.com");
    String url3 = xUrl.registerNewUrl("http://abc3.com");
    String url4 = xUrl.registerNewUrl("http://abc2.com");  
    System.out.println(url);
    System.out.println(url1);
    System.out.println(url2);
    System.out.println(url3);
    System.out.println(url4);

 
    String url5 = xUrl.registerNewUrl("http://abc5.com", "http://short.url/test1");
    String url6 = xUrl.registerNewUrl("http://abc6.com", "http://short.url/test2");
   
    String urlNull = xUrl.registerNewUrl("http://abc7.com", url3);
    assert(urlNull == null);

    System.out.println(url5);
    System.out.println(url6);
    System.out.println(urlNull);
   
    assert(xUrl.getUrl(url).equals("http://abc.com"));
    assert(xUrl.getUrl(url2).equals(xUrl.getUrl(url4)));
    assert(xUrl.getUrl(url5).equals("http://abc5.com"));
    
   
    assert xUrl.getHitCount("http://abc2.com").equals(2);
   
    assert xUrl.getHitCount("http://abcn.com").equals(0);
    

   
    String choppedUrl = url1.replace("http://short.url/", "").replaceAll("[^A-Za-z0-9]", "_");
    System.out.println(choppedUrl);
   
    assert (choppedUrl.length() == 9);

   
    xUrl.delete("http://abc6.com");
    assert (xUrl.getUrl(url6) == null);
  }
}

