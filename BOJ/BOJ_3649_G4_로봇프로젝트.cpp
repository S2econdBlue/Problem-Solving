#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <bits/stdc++.h>


using namespace std;
int arr[1000050];

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int width = 0;

	while (1) {
		int legoCnt;
		cin >> width;
		if (cin.eof())
			break;
		cin >> legoCnt;

		for (int i = 0; i < legoCnt; i++) {
			cin >> arr[i];
		}
		if (legoCnt == 0 || legoCnt == 1) {
			cout << "danger\n";
			continue;
		}
		sort(arr, arr + legoCnt);
		bool isChk = false;
		for (int i = 0; i < legoCnt - 1; i++) {
			int left = i + 1;
			int right = legoCnt - 1;

			int destNum = abs(width * 10000000 - arr[i]);
			while (true) {
				if (left > right)
					break;
				int mid = (left + right) / 2;
				if (arr[mid] == destNum) {
					cout << "yes " << min(arr[i], destNum) << " " << max(arr[i], destNum) << "\n";
					isChk = true;
					break;
				}
				if (destNum < arr[mid]) {
					right = mid - 1;
				}
				else if (arr[mid] < destNum) {
					left = mid + 1;
				}
			}
			if (isChk)
				break;
		}
		if (!isChk)
			cout << "danger\n";
	}
}
