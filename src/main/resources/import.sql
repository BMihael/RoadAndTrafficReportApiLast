insert into role (id, name) values (1, 'ROLE_ADMIN');
insert into role (id, name) values (2, 'ROLE_USER');

insert into user_settings (id, first_name, last_name, phone, address, postcode, email, country) values (1, 'Mihael', 'Belko', '099', 'Zagreb', '49', 'miha@gmail.com', 'Croatia')

insert into user (id, email, name, password, username, user_settings_id) values (1, 'miha@gmail.com', 'miha', '$2a$12$K4JXl1eoxHgBzT9BXEpFUeO2l81OfUVI8ud76VWtT2gGtrb00DDbm', 'mihael', 1);

insert into user_roles (user_id, roles_id) values (1,1)
insert into user_roles (user_id, roles_id) values (1,2)


insert into user_settings (id, first_name, last_name, phone, address, postcode, email, country) values (2, 'Kiki', 'Belko', '098', 'Marija Bistrica', '78', 'kiki@gmail.com', 'Croatia')

insert into user (id, email, name, password, username, user_settings_id) values (2, 'kiki@gmail.com', 'kiki', '$2a$12$THE9BTYXjlXNO52rpJ1/.eHj7H2OO507JGwhfjIFpeKH8WUSCIaLm', 'kiki', 2);

insert into user_roles (user_id, roles_id) values (2,2)


insert into location (id, latitude, longitude) values (1, 45.815010, 15.981919)
insert into location (id, latitude, longitude) values (2, 45.810471, 15.925395)
insert into location (id, latitude, longitude) values (3, 45.839181, 15.958737)
insert into location (id, latitude, longitude) values (4, 45.327065, 14.442176)
insert into location (id, latitude, longitude) values (5, 45.335592, 14.425117)
insert into location (id, latitude, longitude) values (6, 40.712776, -74.005974)
insert into location (id, latitude, longitude) values (7, 40.743199, -73.987665)
insert into location (id, latitude, longitude) values (8, 40.684387, -73.997632)
insert into location (id, latitude, longitude) values (9, 40.735395, -74.042998)
insert into location (id, latitude, longitude) values (10, 40.777786, -74.012754)


insert into submit (id, date, description, title, type, location_id, user_id) values (1,'2022-01-10','cool','Boston', 'ROAD_WORK', 1 ,1)
insert into submit (id, date, description, title, type, location_id, user_id) values (2,'2022-07-26','big bang','New York', 'ROAD_WORK', 2 ,1)
insert into submit (id, date, description, title, type, location_id, user_id) values (3,'2025-01-10','car','New York', 'ROAD_WORK', 3 ,1)
insert into submit (id, date, description, title, type, location_id, user_id) values (4,'2023-04-10','duck','Chicago', 'ROAD_ACCIDENT', 4 ,1)
insert into submit (id, date, description, title, type, location_id, user_id) values (5,'2022-01-15','tower big description big description big description big description big description big description big description big description big description big description big description','Zagreb', 'ROAD_WORK', 5 ,1)
insert into submit (id, date, description, title, type, location_id, user_id) values (6,'2023-01-10','plane','Otawa', 'ROAD_ACCIDENT', 6 ,1)
insert into submit (id, date, description, title, type, location_id, user_id) values (7,'2024-05-17','random','Dublin', 'ROAD_ACCIDENT', 7 ,1)
insert into submit (id, date, description, title, type, location_id, user_id) values (8,'2022-01-28','Nice','Mexico City', 'ROAD_WORK', 8 ,1)
insert into submit (id, date, description, title, type, location_id, user_id) values (9,'2022-01-28','Nice','Mexico City', 'ROAD_WORK', 9 ,1)
insert into submit (id, date, description, title, type, location_id, user_id) values (10,'2022-01-28','Nice','Mexico City', 'ROAD_WORK', 10 ,1)

