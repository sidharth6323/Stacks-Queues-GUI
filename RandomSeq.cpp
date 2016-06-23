#include <cmath>
#include <stdlib.h>
#include <iostream>
#include <algorithm>
#include <time.h>
#define rep(i,n) for(int i=0;i<n;i++) // rep(i,n) is replaced by the for loop
using namespace std;

//Algorithm 1 : O(n), uses a double ended queue 
void random_seq1(int a[],int n){
	int deq[n],first=-1,last=n;
	time_t t=time(NULL); //Gets the time in seconds
	double x; 
	rep(i,n){
		x=100*sin(a[i]*t); //random value
		if(x>0) deq[++first]=a[i]; //inserts element at the front
		else deq[--last]=a[i]; //inserts element at the rear
	}
	first=0;
	last=n-1;
	while(first<=last){
		x=100*sin(x*t); //random value
		if(x>0) 
			cout<<deq[first++]<<" "; //prints the element at the front 
		else 
			cout<<deq[last--]<<" "; //prints the element at the rear
	}
}

struct arr{
    int index;
    double val;
    //operator< overloading for the stable_sort() function
    bool operator<(const arr& x) const{
        return this->val<x.val;
    }
};

//Algorithm 2 : O(nlogn) to O(n^2), depending on stable_sort() function, uses array of structures and sorting of random values obtained
void random_seq2(int a[],int n){
    arr x[n];
    time_t t=time(NULL); //Gets the time in seconds
    rep(i,n){
        x[i].index=i;
        x[i].val=100*sin(t*a[i]);
    }
    stable_sort(x,x+n);  //stable_sort() function in "algorithm" header file, O(nlogn) to O(n^2)
    rep(i,n)
        cout<<a[x[i].index]<<" ";
}

//Algorithm 3 : O(n), uses rand( ) built-in function, and swapping of indices
void random_seq3(int a[],int n){
	int index[n];
	rep(i,n)
		index[i]=i;
	srand(time(0));
	int random,temp;
	rep(i,n){
		random=rand()%n;
		temp=index[random];
		index[random]=index[i];
		index[i]=temp;
	}
	rep(i,n)
		cout<<a[index[i]]<<" ";
}

int main() {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */   
    int n;
	cout<<"Enter number of elements in the sequence :";
    cin>>n;
    int a[n];
	cout<<"Enter the elements of the sequence :"<<endl;
    rep(i,n) 
		cin>>a[i];
	cout<<"Output from Algorithm 1 : "<<endl;
    random_seq1(a,n);
    cout<<endl;
	cout<<"Output from Algorithm 2 : "<<endl;
    random_seq2(a,n);
	cout<<endl;
	cout<<"Output from Algorithm 3 : "<<endl;
	random_seq3(a,n);
    return 0;
}