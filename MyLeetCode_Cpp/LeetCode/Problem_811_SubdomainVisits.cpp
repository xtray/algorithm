// LeetCode Template
// Created by dvlcis @ 2022/10/5 12:05
// 

#include  <stdio.h>
#include  <string>
#include  <iostream>
#include  <vector>
#include  <unordered_map>
#include  <set>

using namespace std;

class TrieNode {
public:
    int sum; // 节点累加值
    unordered_map<string, TrieNode *> nexts;

    TrieNode() {
        sum = 0;
    }
};

//字符串分割函数
std::vector<std::string> split(std::string str, std::string pattern) {
    std::string::size_type pos;
    std::vector<std::string> result;
    str += pattern;//扩展字符串以方便操作
    int size = str.size();
    for (int i = 0; i < size; i++) {
        pos = str.find(pattern, i);
        if (pos < size) {
            std::string s = str.substr(i, pos - i);
            result.push_back(s);
            i = pos + pattern.size() - 1;
        }
    }
    return result;
}

class Problem_811_SubdomainVisits {
private:
    TrieNode *root = new TrieNode();

    void addNode(int count, string s) {
        TrieNode *cur = root;
        auto ss = split(s, ".");
        for (int i = ss.size() - 1; i >= 0; i--) {
            if (cur->nexts.count(ss[i]) == 0) {
                cur->nexts[ss[i]] = new TrieNode();
            }
            cur = cur->nexts[ss[i]];
            cur->sum += count;
        }
    }

public:


    void dfs(TrieNode *root, string path, vector<string>& ans) {
        if (root == nullptr) return;
        for (auto &&[subdomain, node]: root->nexts) {
            int cnt = node->sum;
            string domain;
            if (path.empty()) {
                domain = subdomain;
            } else {
                domain = subdomain + "." + path;
            }
            ans.emplace_back(to_string(cnt) + " " + domain);
            dfs(root->nexts[subdomain], domain, ans);
        }

    }

    vector<string> subdomainVisits(vector<string> &cpdomains) {
        for (auto &&s: cpdomains) {
            int spPos = s.find(' ');
            int count = stoi(s.substr(0, spPos));
            string domain = s.substr(spPos + 1);
            addNode(count, domain);
        }
        vector<string> ans;
        dfs(root, "", ans);
        return ans;
    }
};

// for test
int main() {
    Problem_811_SubdomainVisits *sl = new Problem_811_SubdomainVisits();
    vector<string> cpdomains = {"9001 discuss.leetcode.com"};
    auto ans = sl->subdomainVisits(cpdomains);
    for (auto &s: ans) {
        cout << s << " ,";
    }
    cout << endl;
    return 0;
}