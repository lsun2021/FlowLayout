package com.zimo.myhobby.widget;

import java.io.Serializable;

/**
 * Created by ${Zimo} on 2017/5/8.
 * title:
 */

public class HobbyTagBean  implements Serializable {
    private String tag;

    private boolean isOnFirst=false;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isOnFirst() {
        return isOnFirst;
    }

    public void setOnFirst(boolean onFirst) {
        isOnFirst = onFirst;
    }


    @Override
    public boolean equals(Object obj) {
        if(obj  instanceof   String){
           if(tag.equals(obj)){
               return  true;
           }else
               return  false;
        }
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HobbyTagBean other = (HobbyTagBean) obj;
        if(tag==null){
            if (other.tag != null)
                return false;
        } else if (!tag.equals(other.tag))
            return false;
        return true;
    }
}
