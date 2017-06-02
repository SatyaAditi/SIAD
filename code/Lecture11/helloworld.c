#include<stdio.h>
#include<stdlib.h>

int main(void)
{
printf("Content-Type: text/plain; charset=utf-8 \n\n");

char username[100];
char password[100];
printf("\n Hello world \n");

char *data = getenv("QUERY_STRING");
//printf("%s",data);
sscanf(data,"name=%[^&]&password=%s",username,password);
printf("username is %s \n",username);
printf("password is %s \n",password);
if(password[0]=='\0')
{
printf("Enter password and username");
}
else
{

printf("\n Hello world \n");
}

return 0;
}

