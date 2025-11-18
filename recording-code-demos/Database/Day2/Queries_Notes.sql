/*
JOINS

INNER JOIN
	- Returns rows that interset between first table and second table
		SELECT Album.AlbumId, Album.Title, Track.Name
		FROM Album
		INNER JOIN Track ON Album.AlbumId = Track.AlbumId;	
		
LEFT JOIN
	- return all rows from the first table excluding any rows that intersect with the right table 
	- return all rows from the first table including any rows that intersect from the right table

RIGHT JOIN
	- return all rows from the first table excluding any rows that intersect with the left table 

SELF JOIN
	- used to create comprimised data from a single row on a single table

FULL JOIN
	- used to return all rows from both tables

Set Operators

UNION
	- used to combine two or more SELECT statements 
	- every SELECT statement within UNION needs to have the same number of columns
	- needs to have same data type
	
	SELECT column1, column2 FROM table1
	UNION
	SELECT column1, column2 FROM table2
	
UNION ALL
	- same as UNION ALL, except it retrieves duplicates
	
	SELECT column1, column2 FROM table1
	UNION ALL
	SELECT column1, column2 FROM table2
	
INTERSECT
	- used to retrieve rows that are identical between the 2 SELECT statements
	
	SELECT column1, column2, column3 
	FROM TableN, TableN
	INTERSECT 
	SELECT column1, column2, column3 
	FROM TableN, TableN
	
	
EXCEPT
	- Used to return all rows in the first SELECT statement that are not returned by the second SELECT statement
	- need to have the same number of fields and data types in the set results
	
	SELECT expression
	FROM tableN
	WHERE conditions
	EXCEPT
	SELECT expression
	FROM tableN
	WHERE conditions

*/

SELECT "Album"."AlbumId", "Album"."Title", "Track"."Name"
FROM "Album"
INNER JOIN "Track" ON "Album"."AlbumId" = "Track"."AlbumId";

SELECT "Playlist"."PlaylistId", "Playlist"."Name"
FROM "Playlist"
LEFT JOIN "PlaylistTrack" ON "Playlist"."PlaylistId" = "PlaylistTrack"."PlaylistId";

SELECT *
FROM "Playlist"
RIGHT JOIN "PlaylistTrack" ON "Playlist"."PlaylistId" = "PlaylistTrack"."PlaylistId";


SELECT emp."FirstName" || ' ' || emp."LastName" as EmployeeName,
 	   emp."Title", 
	   boss."FirstName" || ' ' || boss."LastName" as Boss, boss."Title"
FROM "Employee" emp 
LEFT JOIN "Employee" boss ON emp."ReportsTo" = boss."EmployeeId"; 

SELECT * 
FROM "Employee";


SELECT *
FROM "Employee" emp 
INNER JOIN "Employee" boss ON emp."ReportsTo" = boss."EmployeeId"; 




