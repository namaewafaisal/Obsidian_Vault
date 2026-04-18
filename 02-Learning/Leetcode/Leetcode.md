date 19/12/25
problem 643. Maximum Average Subarray I - sliding window
```java
class Solution {
	public double findMaxAverage(int[] nums, int k) {
		int left = 0, right = 0;
		double maxAvg = -Double.MAX_VALUE; // used because Double.MIN_VALUE is 0.000000 like that and not -IntegerValue.000
		int currSum = 0;
		while(right < nums.length) {
			currSum += nums[right];
			if(right - left + 1 == k) {
				maxAvg = Math.max(maxAvg, (double) currSum / k);
				currSum -= nums[left++];
			}
			right++;
		}	
		return maxAvg;
	}
}
```

1343. Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold

```java
class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int left = 0, right = 0;
        int currSum = 0;
        int count = 0;

        while (right < arr.length) {
            currSum += arr[right];

            if (right + 1 - left == k) {
                if (currSum / (double) k >= threshold) { // better way is currSum >= threshold * k because c/k = t == c = t*k  
                    count++;
                }
                currSum -= arr[left++];
            }
            right++;
        }
        return count;
    }
}
```

1876. Substrings of Size Three with Distinct Characters

```java
import java.util.HashMap;
class Solution {
    public int countGoodSubstrings(String s) {
        Map<Character, Integer> freq = new HashMap<>();

        int left = 0, right = 0;
        int count = 0;
        int size = s.length();
        int k = 3;

        while(right < size) {
            char r = s.charAt(right);
            freq.put(r,freq.getOrDefault(r,0)+1);

            if(right + 1 - left == k){
                if(freq.size() == k) {
                    count++;
                }
                char l = s.charAt(left);
                freq.put(l,freq.get(l) - 1);
                if(freq.get(l) == 0){
                    freq.remove(l);
                }
                left++;
            }
            right++;
        } 
        return count;
    }
}
```

day 20/12/25

leetcode 3. Longest Substring Without Repeating Characters (5-10 attempts)
v1
```java
import java.util.HashMap;
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        int right = 0, left = 0;
        int count = 0;
        int size = s.length();

        while(right < size) {
            char r = s.charAt(right);
            freq.put(r,freq.getOrDefault(r,0)+1);

            if(right - left + 1 == freq.size()) {
                count++;
                right++;
            }
            else{
                char l = s.charAt(left++);
                freq.put(l, freq.get(l)-1);
                if(freq.get(l) == 0) {
                    freq.remove(l);
                }
                right++;
            }
        }
        return count;
    }
}

```
1. Does not repeatedly remove left as long as invalid before adding the right
2. Dup may slip because we dont check if the right exist already

Final Version
```java
class Solution2 {
	public int lengthOfLongestSubstring(String s) {
		Map<Character, Integer> freq = new HashMap<>();
		int right = 0, left = 0;
		int len = 0;
		int size = s.length();
		while(right < size) {
			char r = s.charAt(right);
			freq.put(r,freq.getOrDefault(r,0)+1);
			while(freq.get(r) > 1) {
				char l = s.charAt(left++);
				freq.put(l, freq.get(l)-1);
				if(freq.get(l) == 0) {
					freq.remove(l);
				}
			}
			len = Math.max(len, right + 1 - left);
		right++;
		}
	return len;
	}
}
```

1. Never let a dupe inside the window
2. remove all the dupes of right by adding right


# 209. Minimum Size Subarray Sum

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int right = 0, left = 0;
        int len = nums.length+1;
        int sum = 0;

        while(right<nums.length) {
            sum += nums[right];

			/* my solution
            while(sum - nums[left] >= target) {
                sum -= nums[left++];
            }
            if(sum >= target){
                len = Math.min(len, right - left + 1);
                if(len == 1){
                    return 1;
                }
            }*/
            
            //chatgpt solution We record the valid len and break valid window intentionaly
            while(sum>=target) {
	            len = Math.min(len, right - left + 1);
	            sum-=nums[left++]
            }
            right++;
        }
        if (len > nums.length){
            return 0;
        }
        return len;
    }
}
```

# 424. Longest Repeating Character Replacement

```java
import java.util.Map;
import java.util.HashMap;
class Solution {
    public int characterReplacement(String s, int k) {
        int right = 0, left = 0;
        int len = 0;
        int size = s.length();
        Map<Character, Integer> map = new HashMap<>();

        int maxFreq = 0;
        while(right < size) {
            char r = s.charAt(right);
            map.put(r, map.getOrDefault(r,0)+1);
            maxFreq = Math.max(map.get(r),maxFreq);

            while(right+1-left > maxFreq + k) {
                char l = s.charAt(left++);
                /* my sol, maxfreq is allowed to be wrong but still doesnt affect len until its valid
                if(map.get(l) == maxFreq) {
                    maxFreq = Math.max(map.get(s.charAt(left)),maxFreq);
                }
                */
                map.put(l, map.get(l)-1);

            }
            len = Math.max(len, right+1-left);

            right++;
        }
        return len;
    }
}
```


This is **well written**, and you are very close to a reusable standard.  
I’ll now give you **Obsidian-ready entries** for the remaining problems, **aligned exactly** with how you reasoned in our previous conversations.

You can paste each as a separate note.

---


---


---


---



---

## LeetCode 209 — Minimum Size Subarray Sum

(**You wrote 203, but based on code and discussion, this is 209**)

> Date 2025-12-20

### Problem (summary)

Given an array of positive integers and a target sum,  
find the **minimum length** of a contiguous subarray whose sum ≥ target.

---

### Example

Input: `target = 7, nums = [2,3,1,2,4,3]`  
Output: `2`  
Explanation: `[4,3]`

---

### Pattern Classification

- Sum based constraint
    
- Min window
    
- Variable size window
    

---

### Key Invariant

- Window is valid if  
    `window sum ≥ target`
    

---

### Shrinking Rule

- While the window is valid:
    
    - update minimum length
        
    - shrink from the left to minimize window
        

---

### Important Insight

- This is the **inverse** of max window problems
    
- Valid window ⇒ try shrinking further
    

---

### Code

```java
// your solution
```

#### Tags

1. #SlidingWindow
    
2. #VariableSize
    
3. #MinWindow
    

---

### Final Note (important)

You now have:

- Fixed size window
    
- Variable size window
    
- Max window
    
- Min window
    
- Frequency-based constraints
    
- Sum-based constraints
    

This is the **entire sliding window foundation**.

Next logical step:  
👉 **Two pointers without frequency maps**

When ready, say:  
**“start two pointers — sorted arrays”**