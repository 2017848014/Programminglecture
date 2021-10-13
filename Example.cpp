#include <stdio.h>
#include <time.h>
#include <Windows.h>

void seat_arrangement(int row, int column, int student);
void shuffle_number(int sit_number[], int student);
void draw_check02(int column, int row);
void gotoxy(int x, int y);

int main(void)
{
        int row, column, student;
        printf("�ڸ���ġ ���α׷�\n");
        printf("�¼���ġ�� ���� ��� ���� �Է��ؾ� �մϴ�\n");
        printf("�л����� �ִ� 100���Դϴ�.(10X10)\n");
        printf("���� ���� �Է��ϰ� Enter>");
        scanf("%d", &row);
        printf("���� ���� �Է��ϰ� Enter>");
        scanf("%d", &column);
        printf("�л� ���� �Է��ϰ� Enter>");
        scanf("%d", &student);
        seat_arrangement(row, column, student);
        printf("\n\n\n");
        return 0;
}
void gotoxy(int x, int y)
{
        COORD Pos = { x - 1, y - 1 };
        SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), Pos);
}
void draw_check02(int c, int r)
{
  //[�Լ� 3.5.4]�� ���� �κ� ����
    int i, j;
    unsigned char a=0xa6;
    unsigned char b[12]; 
    for(i=1;i<12;i++)
	b[i]=0xa0+i;
    printf("%c%c",a, b[3]);
    for(i=0;i<c*2-1;i++)  //������ ������ 
    {
	printf("%c%c", a, b[1]); 
	printf("%c%c", a, b[1]);
    }
    printf("%c%c", a, b[1]);
    printf("%c%c", a, b[4]);  //������� ���ظ�� 
    printf("\n");
    for(i=0;i<r-1;i++) //�� �ݺ��� 
    {
	printf("%c%c", a, b[2]);
	for(j=0;j<c+1;j++)
	{   
		printf("  ");
	}    
	printf("\n");
	printf("%c%c", a, b[7]);
	for(j=0;j<c*2-1;j++)  //2���ٺ��� ������ 
	{   
		printf("%c%c", a, b[1]);
		printf("%c%c", a, b[1]);
	}
	printf("%c%c",a, b[1]);
	printf("%c%c", a, b[9]);
	printf("\n");
    }
    printf("%c%c", a, b[2]);
    for(j=0;j<c+1;j++) //�������� ������ 
    {
	printf("  ");
    }
    printf("\n");
	printf("%c%c", a, b[6]);
    for(i=0;i<c*2-1;i++)  //�������� ������ 
    {
	printf("%c%c", a, b[1]);
	printf("%c%c", a, b[1]);
    }
    printf("%c%c", a, b[1]);
    printf("%c%c", a, b[5]);
    printf("\n");
}
void shuffle_number(int sit_number[], int student)
{
    int i, rnd, temp;
    for (i = 0; i < student; i++)
    {
        rnd = rand() % student;
        temp = sit_number[rnd];
        sit_number[rnd] = sit_number[i];
    	sit_number[i] = temp;
   }
}
void seat_arrangement(int row, int column, int student)
{
        int sit_number[100] = { 0 };
        int i, j, count = 0;
        for (i = 0; i < student; i++)
               sit_number[i] = i + 1;
        shuffle_number(sit_number, student);
        gotoxy(1, 7);
        draw_check02(column, row);
        for (i = 1; i <= row; i++)
        {
               for (j = 1; j <= column; j++)
               {
                    gotoxy(j * 4 - 1, 6 + i * 2);
                    printf("%2d", sit_number[count]);
                    count++;
                    if (count==student)
                    return;
               }
        }
}

