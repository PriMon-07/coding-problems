package general;

import java.util.*;

class LFUCache {

    //      key - >{ value, userCount}
    HashMap<Integer,Pair<Integer,Integer>> hm = new LinkedHashMap<>();
    HashMap<Integer, List<Integer>> userCountMap = new HashMap<>();
    int siz = 0;

    public LFUCache(int capacity) {
        siz = capacity;
    }

    public int get(int key) {
        if(siz==0)
            return -1;
        if(!hm.containsKey(key))
            return -1;
        int userCount = hm.get(key).getValue();
        hm.put(key,new Pair(hm.get(key).getKey(),userCount+1));

        if(!userCountMap.containsKey(userCount+1))
            userCountMap.put(userCount+1,new ArrayList<>());
        userCountMap.get(userCount+1).add(key);
        userCountMap.get(userCount).remove(userCountMap.get(userCount).indexOf(key));
        return hm.get(key).getKey();
    }

    public void put(int key, int value) {
        if(siz==0)
            return ;
        if(hm.size()<siz){
            if(!hm.containsKey(key)){
                hm.put(key,new Pair(value,1));
                if(!userCountMap.containsKey(1) ||
                        userCountMap.get(1).size()==0)
                    userCountMap.put(1, new ArrayList<>());
                List<Integer> temp = userCountMap.get(1);
                temp.add(key);
                userCountMap.put(1,temp);
            }
            else{
                int uc = hm.get(key).getValue();
                int v = hm.get(key).getKey();
                if(!userCountMap.containsKey(uc+1))
                    userCountMap.put(uc+1,new ArrayList<>());
                List<Integer> temp = userCountMap.get(uc+1);
                temp.add(key);
                userCountMap.put(uc+1,temp);
                userCountMap.get(uc).remove(userCountMap.get(uc).indexOf(key));
                hm.put(key,new Pair(value,uc+1));
            }
        }else if(hm.size()==siz && hm.containsKey(key)){
            int uc = hm.get(key).getValue();
            hm.put(key, new Pair(value,uc+1));
            userCountMap.get(uc).remove(userCountMap.get(uc).indexOf(key));
            if(!userCountMap.containsKey(uc+1))
                userCountMap.put(uc+1, new ArrayList<>());
            List<Integer> temp = userCountMap.get(uc+1);
            temp.add(key);
            userCountMap.put(uc+1,temp);
        }else{
            Iterator itr =  userCountMap.entrySet().iterator();
            int rk = -1, ruc = -1;
            while(itr.hasNext()){
                Map.Entry me = (Map.Entry) itr.next();
                ruc = (int)me.getKey();
                if(userCountMap.get(ruc).size()==0)
                    continue;
                rk = (int)userCountMap.get(ruc).get(0);
                break;
            }
            hm.remove(rk);
            userCountMap.get(ruc).remove(userCountMap.get(ruc).indexOf(rk));
            if(!hm.containsKey(key)){
                hm.put(key,new Pair(value,1));
                if(!userCountMap.containsKey(1) ||
                        userCountMap.get(1).size()==0)
                    userCountMap.put(1, new ArrayList<>());
                List<Integer> temp = userCountMap.get(1);
                temp.add(key);
                userCountMap.put(1,temp);
            }
            else{
                int uc = hm.get(key).getValue();
                int v = hm.get(key).getKey();
                if(!userCountMap.containsKey(uc+1))
                    userCountMap.put(uc+1,new ArrayList<>());
                List<Integer> temp = userCountMap.get(uc+1);
                temp.add(key);
                userCountMap.put(uc+1,temp);
                userCountMap.get(uc).remove(userCountMap.get(uc).indexOf(key));
                hm.put(key,new Pair(value,uc+1));
            }

        }
    }

    public static void main(String[] args) {

//          Your LFUCache object will be instantiated and called as such:
         LFUCache obj = new LFUCache(2);
         int param_1 = obj.get(2);
         obj.put(1,3);
        obj.put(2,4);
        obj.put(5,5);
         int param_2 = obj.get(2);
        System.out.println(param_1);
        System.out.println(param_2);
    }


}

class Pair<K, V> {
    V value;
    K key;

    public Pair(K key, V value) {
        this.value = value;
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }
}