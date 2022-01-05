/**
 * Author: Mahad Aziz - azizm17
 * Revised: March 29, 2021
 * 
 * Description: AttributeT ADT class
 */

import java.util.Arrays;
import java.util.HashSet;

public class AttributeT{
    private String name;
    private HashSet<IndicatorT> s;

    public AttributeT(String attribName,IndicatorT[]indicators){
        this.name = attribName;
        this.s = new HashSet<>(Arrays.asList(indicators));
    }
    public String getName(){
        return name;
    }
    public IndicatorT[] getIndicators() {
        return s.toArray(new IndicatorT[s.size()]);
    }
}
