select c.name, o.product_name
from orders o join (select id, name from customers where lower(name) = lower(:name)) c on o.customer_id = c.id
order by o.product_name;