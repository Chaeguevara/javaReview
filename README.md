# javaReview
자바의 정석


# [Chapter2](./Hello/src/com/class2)

- final 자료형 변수 = 리터럴

final은 변수가 상수가 되도록 함. 한번 값을 지정하면 변경 불가능함 


# 자료형 이해

|        | 1 byte  | 2 byte | 4 byte | 8 byte |
| ------ | ------- | ------ | ------ | ------ |
| 논리형 | boolean |        |        |        |
| 문자형 |         | char   |        |        |
| 정수형 | byte    | short  | **int**    | long   |
| 실수형 |         |        | float  | **double** |

bit = 8 byte
$$
표현 가능 범위 = -2^{bit} ~ 2^{bit} +1
$$

int는 약 +- 20억 표현 가능
