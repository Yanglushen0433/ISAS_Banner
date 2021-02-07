/**
  * Copyright 2021 jb51.net 
  */
package report2;
import java.util.List;

/**
 * Auto-generated: 2021-01-30 14:30:50
 *
 * @author jb51.net (i@jb51.net)
 * @website http://tools.jb51.net/code/json2javabean
 */
public class JsonRootBean {

    private int total;
    private int available;
    private List<Matches> matches;
    private Facets facets;
    public void setTotal(int total) {
         this.total = total;
     }
     public int getTotal() {
         return total;
     }

    public void setAvailable(int available) {
         this.available = available;
     }
     public int getAvailable() {
         return available;
     }

    public void setMatches(List<Matches> matches) {
         this.matches = matches;
     }
     public List<Matches> getMatches() {
         return matches;
     }

    public void setFacets(Facets facets) {
         this.facets = facets;
     }
     public Facets getFacets() {
         return facets;
     }

}