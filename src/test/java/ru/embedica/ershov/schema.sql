
create table CAR (
                        id SERIAL primary key,
                        car_number varchar(15) UNIQUE,
                        brand varchar(50),
                        color varchar(50),
                        year_of_issue SMALLINT,
                        date_added timestamp DEFAULT now()

);

