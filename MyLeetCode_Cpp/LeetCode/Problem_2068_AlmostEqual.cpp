#include <string>
#include <unordered_map>

using namespace std;

class Problem_2068_AlmostEqual {
public:

    bool checkAlmostEquivalent(string word1, string word2) {
        int cnt[26] = {0};
        for (char ch: word1) {
            cnt[ch - 'a']++;
        }
        for (char ch: word2) {
            cnt[ch - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (abs(cnt[i]) > 3) {
                return false;
            }
        }
        return true;
    }


    bool checkAlmostEquivalent2(string word1, string word2) {
        unordered_map<char, int> freq;   // 频数差哈希表
        for (auto ch: word1) {
            ++freq[ch];
        }
        for (auto ch: word2) {
            --freq[ch];
        }
        // 判断每个字符频数差是否均小于等于 3
        return all_of(freq.cbegin(), freq.cend(), [](auto &&x) { return abs(x.second) <= 3; });
    }

};
