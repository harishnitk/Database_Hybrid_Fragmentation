/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package InputFormats;
import hybridfragmentation.*;

/**
 *
 * @author sony
 */
public class predicateAffMatrix {
    public String implies;
    public String close;
    public int value;

    public predicateAffMatrix() {
    }

    public predicateAffMatrix(String implies,String close,int value)
    {
        this.implies = implies;
        this.close = close;
        this.value = value;
    }
}
