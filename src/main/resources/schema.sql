create table customers (
                           id serial primary key,
                           name varchar(50) not null,
                           surname varchar(50) not null,
                           age int check ( age > 18 and age < 150) not null,
                           phone_number char(11) not null
);
create index name_index on customers(name);

create table orders(
  id serial primary key,
  date date check ( date > '2020.01.01') not null,
  customer_id bigint unsigned not null,
  product_name varchar(200) not null,
  amount dec(10, 2) not null,
  foreign key (customer_id) references customers(id)
);