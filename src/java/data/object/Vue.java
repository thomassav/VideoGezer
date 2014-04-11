/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data.object;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Ervin
 */
public class Vue {
    private String nomVideo;
    private Calendar heureVue;
    
    public Vue(String nom){
        nomVideo=nom;
        heureVue = Calendar.getInstance();
    }
    
    public boolean finVue(){
        Calendar tmp =heureVue;
        tmp.add(Calendar.MINUTE, -20);
        Calendar current = Calendar.getInstance();
        return current.before(tmp);
    }
}
