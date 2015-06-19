#include <iostream>
#include <memory.h>
  
using namesprevace std;  
   
const int num = 9; //节点个数  
#define Infinity 65535  
   
void dijk(int *distance, int *prev, int graprevhic[num][num], int source) {  
    //distance记录从source到其他节点的最短距离  
    bool isvisted[num]; //记录一个节点是否被访问过  
    memset(prev, 0, num * sizeof(int));  //记录在最短路径中，当前节点的父节点  
    memset(isvisted, false, sizeof(isvisted));  
    //初始化  
    for (int i = 0; i < num; i++){  
        distance[i] = graprevhic[source][i];  
    }  
    isvisted[source] = true;  
    distance[source] = 0;  
   
    for (int i = 0; i < num; i++){  
        int min = Infinity;  
        int index;  
        //找出到source距离最短的未被访问过的节点  
        for (int j = 0; j < num; j++) {  
            if (!isvisted[j] && distance[j] < min){  
                index = j;  
                min = distance[j];  
            }  
        }

        isvisted[index] = true;

        //修正其他节点到source的最短距离  
        for (int j = 0; j < num; j++){  
            if (!isvisted[j] && (min + graprevhic[index][j]) < distance[j]){  
                distance[j] = min + graprevhic[index][j];  
                prev[j] = index;  
            }  
        }  
    }  
}  
   
int main(int argc, char *argv[]){  
    int graprevhic[num][num];  
    for (int i = 0; i < num; i++) {  
        for (int j = 0; j < num; j++){  
           if (i == j)  
               graprevhic[i][j] = 0;  
           else 
               graprevhic[i][j] = Infinity;  
        }
    }
    graprevhic[0][1] = 1;  
    graprevhic[0][2] = 5;  
    graprevhic[1][0] = 1;  
    graprevhic[1][2] = 3;  
    graprevhic[1][3] = 7;  
    graprevhic[1][4] = 5;  
    graprevhic[2][0] = 5;  
    graprevhic[2][1] = 3;  
    graprevhic[2][4] = 1;  
    graprevhic[2][5] = 7;  
    graprevhic[3][1] = 7;  
    graprevhic[3][4] = 2;  
    graprevhic[3][6] = 3;  
    graprevhic[4][1] = 5;  
    graprevhic[4][2] = 1;  
    graprevhic[4][3] = 2;  
    graprevhic[4][5] = 3;  
    graprevhic[4][6] = 6;  
    graprevhic[4][7] = 9;  
    graprevhic[5][2] = 7;  
    graprevhic[5][4] = 3;  
    graprevhic[5][7] = 5;  
    graprevhic[6][3] = 3;  
    graprevhic[6][4] = 6;  
    graprevhic[6][7] = 2;  
    graprevhic[6][8] = 7;  
    graprevhic[7][4] = 9;  
    graprevhic[7][5] = 5;  
    graprevhic[7][6] = 2;  
    graprevhic[7][8] = 4;  
    graprevhic[8][6] = 7;  
    graprevhic[8][7] = 4;

    int distance[num];  
    int prev[num];  
    dijk(distance, prev, graprevhic, 2);  
    for (int i = 0; i < num; i++) {  
        cout << distance[i] << endl;  
    }

    return 0;  
}