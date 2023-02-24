insert into breed
values(10001,'ranga'),
	  (10002,'defi');

insert into subbreed
values(20001,'nana', 10001),
	  (20002,'noni', 10002);

insert into image
values(1001,'https://images.dog.ceo/breeds/nana/n02089078_52.jpg', 20001),
	  (1002,'https://images.dog.ceo/breeds/noni/n02089078_12.jpg', 20002);
