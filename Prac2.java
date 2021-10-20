class Solution {
    class TrieNode {
        List<String> prefixWords;
        TrieNode[] children;
        
        public TrieNode() {
            children  = new TrieNode[26];
            prefixWords = new ArrayList<>();
        }
        
    }
    public void insert(String word) {
        TrieNode curr = root;
        for(int i = 0 ; i < word.length(); i++) {
            char c = word.charAt(i);
            if(curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new TrieNode();
            }
            curr.prefixWords.add(word);
            curr = curr.children[c - 'a'];
        }
    }
    
    public List<String> startsWith(String prefix) {
        TrieNode current = root;
        
        for(int i = 0 ; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if(current.children[c-'a'] == null) {
                return new ArrayList<>();
            }
            current = current.children[c-'a'];
        }
        return current.prefixWords;
    }
    
    TrieNode root;
    List<List<String>> result;
    public List<List<String>> wordSquares(String[] words) {
        if(words == null) {
            return new ArrayList<>();
        }
        result = new ArrayList<>();
        root = new TrieNode();
        for(String word : words) {
            insert(word);
        }
        int n = words[0].length();
        List<String> list = new ArrayList<>();
        for(String word : words) {
            //action
            list.add(word);
            //recurse
            backtrack(list, n);
            //backtrack
            list.remove(list.size() - 1);
        }
        return result;
    }
    
    public void backtrack(List<String> list, int length) {
        //base Case
        if(list.size() == length) {
            result.add(new ArrayList<>(list));
            return;
        }
        //logic
        StringBuilder sb = new StringBuilder();
        int index = list.size();
        for(String word : list) {
            sb.append(word.charAt(index));
        }
        List<String> words = startsWith(sb.toString());
        for(String word : words) {
            //action
            list.add(word);
            //recurse
            backtrack(list, length);
            //backtrack
            list.remove(list.size() - 1);
            
        }
    }
}


class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
      List<Boolean> result = new ArrayList<>();
        if(queries == null || queries.length == 0) {
            return result;
        }
        
        for(String query : queries) {
            int p1 = 0;
            boolean match = false;
            
            for(int i = 0 ; i < query.length(); i++) {
                char qchar = query.charAt(i);
                if(p1 < pattern.length() && qchar == pattern.charAt(p1)) {
                    p1++;
                    if(p1 == pattern.length()) {
                        match = true;
                    }
                } else if(Character.isUpperCase(qchar)) {
                    match = false;
                    break;
                }
            }
            result.add(match);
        }
        return result;
    }
}