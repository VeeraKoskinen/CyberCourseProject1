# CyberCourseProject1
Main point in this program is to represent some common security flaws. 

# 5 Security Flaws

Vulnerability number one is Missing Funcion Level Access Conrtol (7). While counting how much money all the food and drinks could cost, you might get negative numbers when removing items from your shopping list still after zero items. 
IDENTIFICATION:
1. Go to “localhost:8080/login”
2. Log in with username: ”ted” and password: ”ted”.
3. Then sign someone to the party using enrolment form and press “submit”. 
4. Now you can see button which says: ” Here you are able to count all food and drink costs 😊” and press it.
5. Choose some other item than water from the list and press ”Add to cart”.
6. Then remove this product from your cart twice or more by pressing the ”remove”–button.
7.  And you can see in the down side area that your costs become negative.
	This can be fixed by limiting it in the business logic so that you can not make the removing if there is zero of those products or other option is to remove the item from ”itemCounts” when there is zero of this special item left in the list of your orders. We can also do both to be sure that our software the way we wanted. 
-------------------------------------------
The second flaw to find from the software is Sensitive Data Exposure (6). You can find list of all participants by taking  type “hidden” away and make all information seeing able again by using inspect –tool in HTML-files. Otherwise you will see only those participants whom you have added by yourself into this event. 
IDENTIFICATION:
1. Go to “localhost:8080/login” 
2. Log in using username “ted” and password “ted”. 
3. Then sign someone to the party using enrolment form and clilck “submit”. 
4. Then go again to “localhost:8080/login” 
5. Log in with username “jee” and password “ted”. 
6. Then sign again someone to the party using enrolment form and clilck “submit”.  
7. Now when you look at the list of your “partygroup” you can see only the last person who you signed in as user “jee”. 
8. Then right-click and choose “inspect”. 
9. Next choose “Elements”. 
10. Click to open “body”, click to oped “ul” and click to open first “li” and “div” inside it. 
11. Remove text “ hidden="true"” and press enter. 
12. Now you should see other users friend’s information that you shouldn’t see. (change password back as “ted” again)
We can avoid this kind of problems by making the proper listing in business logic. Then hackers are not able to make easy and quick changes to quite vulnerable HTML-files. So in listing method we should collect only those “signups” who the user have added to his or her group, not all participants from the party as it is now. 
------------------------------------------
The third flaw is Cross-Site Request Forgery (CSRF) (8). 
IDENTIFICATION:
1. Open the program (localhost:8080/login or localhost:8080).
2. Then make a html file in which you include this peace of html code:
'''HTML
<!DOCTYPE html>
<html>
<body>
      <form action=“http://localhost:8080/password/?” method=“GET”>
	<p>Password: <input type=“text” name=“password”/></p>
	<button type=“submit” value=“Change password”/>
      </form>
</body>
</html>
'''HTML
3. Open this file in the same browser with the program.
4. Then write new password to field for it.
5. Open developer tools, click first html open, then body open, next form and last p tag.
6. Remove all those excessive marks that didn’t belong to the text in the html file.
7. Now press the button on page.
8. Next you can choose to go try our old password ted with user ted and it shouldn’t work.
9. Last try log in with username ted and hacked password new.
10. And it works. We have change password for someone else without his or her knowledge.
	This can be avoid by Checking CSRF token and also Checking standard headers to verify the request is same origin as all the others.
----------------------------------
The fourth vulnerability is Broken Authentication and Session Management flaw (2). You can use for example Owasp Zap to find right passwords by fuzzing with big password lists. You need to find only the right username first.
IDENTIFICATION:
1. Open OWASP ZAP (or similar) program.
2. Put the software running in 8080 (different port than zap is using) and open the program on page “locahost:8080/login” (or just “localhost:8080”)
3. Log in as user “ted” with password “ted” and change the password as “president”. Then return to log in –page. 
4. Set up proxies.
5. Refresh the page in the browser you are using and check that everything is working properly (you will get all the information about what is happening in your browser in Zap).
6. Then write “ted” as a username and for example “variable” to password field and press “login”.
7. Select POST line that show up in to Zap and after that select “->Request”.
8. Now you can see text … password=variable and highlight word “variable” by double-clicking .
9. Next right-click and select fuzz.
10. Select now “payloads…” and click “add”.
11. Then change value String to File (and now we’ll want to use text file which includes those 10000 most common passwords (which we have used before).
12. Press “select” and find the right file you want to use. Then press “open”, “add” and “ok”.
13. Now select “options” and put mark to point: Follow Redirects and start fuzzing.
14. That we wont to find the biggest Size of Response Body is the one we are looking for.
15. And we can recognize that it is the one we just changed to teds password.
	One of the reasons why this is possible is that you can try as many times as you want to find the right word without needing to wait at all and without authenticating yourself. So if we put there limitation which puts you to wait for 30 seconds after every fourth try it will be slower and that’s why little harder to get in to the system. Other things we can do is to modified the system so that after few tries you will need to authenticate yourself via your e-mail.
-------------------------------

