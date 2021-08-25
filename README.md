# binaryTree
# for given string with math operations and with positive, negative numbers
	* Construct Binary tree 
	* evaluate tree and produce result 
	
## 1. Environment
* java 1.8
* Spring boot

# 2. How to test.
 we can test 2 ways.

# 2.1 Using commandpromt
if you do not want to open code, just download jar from ``` /src/test/resources/jarFile ``` folder and run with below command from same jar location.

```
java  -jar binaryTree-0.0.1-SNAPSHOT.jar
```

once server is up and running you can use REST api to test.

** [REST API URL](http://localhost:8080/swagger-ui.html#/math-controller/getMathTreeUsingPOST) **

if you are not able to click it, just copy paste this url in google chrome ** http://localhost:8080/swagger-ui.html#/math-controller/getMathTreeUsingPOST **

** see sample screen at /images folder

# 2.2 Using IDE
check out code form git and integrate in your IDE (Eclipse/Intellij).
Just Right click on the class located at ``` org.telepathy.binarytree.MathTreeApp ``` --> Run As java application.
once server is up and running you can use REST API to test it.

# 2.3 Test case
you can use Integration test to test.
refer org.telepathy.binarytree.integrationtest.MathTreeAppIT

# 3. Notes
* for division we can use / or ÷
* for miltiplication we can use * or ×

# 4. Output Tree Representation
the result of this API will provide pre-order, post-order, in-order of the tree. so that it will be easy to validate tree structure.

* actual result is displayed as "value" (Example: -13)