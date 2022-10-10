// LeetCode Template
// Created by dvlcis @ 2022/10/4 21:55
// 

#include  <stdio.h>
#include  <iostream>

using namespace std;

class TreeNode {
public:
    int val;
    TreeNode *left, *right;

    TreeNode(int val) {
        this->val = val;
        this->left = this->right = NULL;
    }
};

class Problem_596_MinSubTreee {
public:

    TreeNode *minNode = nullptr;
    int minVal = INT32_MAX;

    int dfs(TreeNode *root) {
        if (root == nullptr) {
            return 0;
        }
        int cur = root->val + dfs(root->left) + dfs(root->right);
        if (cur < minVal) {
            minVal = cur;
            minNode = root;
        }
        return cur;
    }

    TreeNode *findSubtree(TreeNode *root) {
        if (root == nullptr) {
            return root;
        }
        dfs(root);
        return minNode;
    }
};

// for test
int main() {
    Problem_596_MinSubTreee *sl = new Problem_596_MinSubTreee();
    TreeNode *root = new TreeNode(1);
    root->right = new TreeNode(2);
    auto ans = sl->findSubtree(root);
    cout << ans->val << endl;

    return 0;
}