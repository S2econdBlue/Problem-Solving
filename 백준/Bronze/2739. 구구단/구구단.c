#include <stdio.h>
main(a, j){
	scanf("%d", &a);
	for (j = 1; j < 10; j++){
		printf("%d * %d = %d\n", a,j,a*j);
	}
}