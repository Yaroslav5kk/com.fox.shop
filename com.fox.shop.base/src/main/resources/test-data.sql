/*-------------------------------------users-----------------------------------------*/
INSERT INTO public.users
    (user_id, first_name, last_name, "password", "role", username)
/*--------merchants------------*/
VALUES (1, 'Egor', 'Ponomarev', 'to_set', 'MERCHANT', 'ruccolla_merch'),
       (2, 'Lada', 'Ignevsca', 'to_set', 'MERCHANT', 'kfc_merch'),
       (3, 'Vasya', 'Popic', 'to_set', 'MERCHANT', 'puz_hata_merch'),
/*--------waiters------------*/
       (4, 'Roma', 'Tera', 'to_set', 'WAITER', 'ruccolla_waiter_1'),
       (5, 'Kolya', 'Rock', 'to_set', 'WAITER', 'ruccolla_waiter_2'),
       (6, 'Olya', 'Retka', 'to_set', 'WAITER', 'kfc_waiter_1'),
       (7, 'Lada', 'Petros', 'to_set', 'WAITER', 'kfc_waiter_2'),
       (8, 'Tanya', 'Hobers', 'to_set', 'WAITER', 'puz_hata_waiter_1'),
       (9, 'Kira', 'Visitor', 'to_set', 'WAITER', 'puz_hata_waiter_2');


/*-------------------------------------image-----------------------------------------*/
INSERT INTO public.image
    (id, provider, url, "name", provider_type)
values
/*--------main merchant------------*/
(1, 'Local', 'C:\other\storage\ruccolla_politech_main.jpg', 'ruccolla_politech_main.jpg', 'LOCAL_STORAGE'),
(2, 'Local', 'C:\other\storage\kfc_politech_main.jpg', 'kfc_politech_main.jpg', 'LOCAL_STORAGE'),
(3, 'Local', 'C:\other\storage\puzata_hata_politech_main.jpg', 'puzata_hata_politech_main.jpg', 'LOCAL_STORAGE'),
/*--------main menu------------*/
(4, 'Local', 'C:\other\storage\main_ruccolla_menu.jpg', 'main_ruccolla_menu.jpg', 'LOCAL_STORAGE'),
(5, 'Local', 'C:\other\storage\main_kfc_menu.jpg', 'main_kfc_menu.jpg', 'LOCAL_STORAGE'),
(6, 'Local', 'C:\other\storage\main_puz_hata_menu.jpg', 'main_puz_hata_menu.jpg', 'LOCAL_STORAGE'),
/*--------kitchens------------*/
(7, 'Local', 'C:\other\storage\pizza_ruccolla_kitchen.jpg', 'pizza_ruccolla_kitchen.jpg', 'LOCAL_STORAGE'),
(8, 'Local', 'C:\other\storage\salads_ruccolla_kitchen.jpg', 'salads_ruccolla_kitchen.jpg', 'LOCAL_STORAGE'),
(9, 'Local', 'C:\other\storage\buckets_kfc_kitchen.jpg', 'buckets_kfc_kitchen.jpg', 'LOCAL_STORAGE'),
(10, 'Local', 'C:\other\storage\burgers_kfc_kitchen.jpg', 'burgers_kfc_kitchen.jpg', 'LOCAL_STORAGE'),
(11, 'Local', 'C:\other\storage\garnish_puz_hata_kitchen.jpg', 'garnish_puz_hata_kitchen.jpg', 'LOCAL_STORAGE'),
(12, 'Local', 'C:\other\storage\first_courses_puz_hata_kitchen.jpg', 'first_courses_puz_hata_kitchen.jpg',
 'LOCAL_STORAGE'),
/*--------products------------*/
(13, 'Local', 'C:\other\storage\four_meats_pizza_ruccolla_product.jpg', 'four_meats_pizza_ruccolla_product.jpg',
 'LOCAL_STORAGE'),
(14, 'Local', 'C:\other\storage\five_cheeses_pizza_ruccolla_product.jpg', 'five_cheeses_pizza_ruccolla_product.jpg',
 'LOCAL_STORAGE'),
(15, 'Local', 'C:\other\storage\caesar_salads_ruccolla_product.jpg', 'caesar_salads_ruccolla_product.jpg',
 'LOCAL_STORAGE'),
(16, 'Local', 'C:\other\storage\seafood_salad_salads_ruccolla_product.jpg', 'seafood_salad_salads_ruccolla_product.jpg',
 'LOCAL_STORAGE'),
(17, 'Local', 'C:\other\storage\discovery_bucket_buckets_kfc_product.jpg', 'discovery_bucket_buckets_kfc_product.jpg',
 'LOCAL_STORAGE'),
(18, 'Local', 'C:\other\storage\sanders_bucket_buckets_kfc_product.jpg', 'sanders_bucket_buckets_kfc_product.jpg',
 'LOCAL_STORAGE'),
(19, 'Local', 'C:\other\storage\cheeseburger_burgers_kfc_product.jpg', 'cheeseburger_burgers_kfc_product.jpg',
 'LOCAL_STORAGE'),
(20, 'Local', 'C:\other\storage\burger_burgers_kfc_product.jpg', 'burger_burgers_kfc_product.jpg', 'LOCAL_STORAGE'),
(21, 'Local', 'C:\other\storage\buckwheat_garnish_puz_hata_product.jpg', 'buckwheat_garnish_puz_hata_product.jpg',
 'LOCAL_STORAGE'),
(22, 'Local', 'C:\other\storage\ragout_garnish_puz_hata_product.jpg', 'ragout_garnish_puz_hata_product.jpg',
 'LOCAL_STORAGE'),
(23, 'Local', 'C:\other\storage\brine_first_courses_puz_hata_product.jpg', 'brine_first_courses_puz_hata_product.jpg',
 'LOCAL_STORAGE'),
(24, 'Local', 'C:\other\storage\borsch_first_courses_puz_hata_product.jpg', 'borsch_first_courses_puz_hata_product.jpg',
 'LOCAL_STORAGE'),
/*--------merchant group------------*/
(25, 'Local', 'C:\other\storage\tarantino_merchant_group.jpg', 'tarantino_merchant_group.jpg',
 'LOCAL_STORAGE'),
(26, 'Local', 'C:\other\storage\ukrainian_merchant_group.jpg', 'ukrainian_merchant_group.jpg',
 'LOCAL_STORAGE'),
/*--------product group------------*/
(27, 'Local', 'C:\other\storage\fast_food_product_group.jpg', 'fast_food_product_group.jpg',
 'LOCAL_STORAGE'),
(28, 'Local', 'C:\other\storage\pizza_product_group.jpg', 'pizza_product_group.jpg',
 'LOCAL_STORAGE'),
/*--------waiters------------*/
(29, 'Local', 'C:\other\storage\waiter_1_ruccolla_politech.jpg', 'waiter_1_ruccolla_politech.jpg',
 'LOCAL_STORAGE'),
(30, 'Local', 'C:\other\storage\waiter_2_ruccolla_politech.jpg', 'waiter_2_ruccolla_politech.jpg',
 'LOCAL_STORAGE'),
(31, 'Local', 'C:\other\storage\waiter_1_kfc_politech.jpg', 'waiter_1_kfc_politech.jpg',
 'LOCAL_STORAGE'),
(32, 'Local', 'C:\other\storage\waiter_2_kfc_politech.jpg', 'waiter_2_kfc_politech.jpg',
 'LOCAL_STORAGE'),
(33, 'Local', 'C:\other\storage\waiter_1_puz_hata_politech.jpg', 'waiter_1_puz_hata_politech.jpg',
 'LOCAL_STORAGE'),
(34, 'Local', 'C:\other\storage\waiter_2_puz_hata_politech.jpg', 'waiter_2_puz_hata_politech.jpg',
 'LOCAL_STORAGE'),
/*--------places------------*/
(35, 'Local', 'C:\other\storage\kfc_politech_place_room1.jpg', 'kfc_politech_place_room1.jpg',
 'LOCAL_STORAGE'),
(36, 'Local', 'C:\other\storage\kfc_politech_place_room2.jpg', 'kfc_politech_place_room2.jpg',
 'LOCAL_STORAGE'),
(37, 'Local', 'C:\other\storage\ruccolla_politech_place_room1.jpg', 'ruccolla_politech_place_room1.jpg',
 'LOCAL_STORAGE'),
(38, 'Local', 'C:\other\storage\ruccolla_politech_place_room2.jpg', 'ruccolla_politech_place_room2.jpg',
 'LOCAL_STORAGE'),
(39, 'Local', 'C:\other\storage\puz_hata_politech_place_room1.jpg', 'puz_hata_politech_place_room1.jpg',
 'LOCAL_STORAGE'),
(40, 'Local', 'C:\other\storage\puz_hata_politech_place_room2.jpg', 'puz_hata_politech_place_room2.jpg',
 'LOCAL_STORAGE');


/*-------------------------------------merchant-----------------------------------------*/
INSERT INTO public.merchant
    (id, latitude, longitude, "name", main_image_id)
values (1, 50.450688, 30.467937, 'Ruccolla politech', 1),
       (2, 50.451313, 30.467688, 'Kfc politech', 2),
       (3, 50.451313, 30.467438, 'Puzata hata politech', 3);
/*--------merchnat users------------*/
INSERT INTO public.merchant_users
    (merchant_entity_id, users_user_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);
/*--------merchant places------------*/
INSERT INTO public.merchant_places
    (merchant_entity_id, places_id)
VALUES (1, 37),
       (1, 38),
       (1, 35),
       (1, 36),
       (1, 39),
       (1, 40);

/*-------------------------------------merchant group-----------------------------------------*/
INSERT INTO public.merchant_group
    (id, description, "name", main_image_id)
VALUES (1, 'tarantino', 'tarantino', 25),
       (2, 'ukrainian', 'ukrainian', 26);
/*-----merchant groups-------*/
INSERT INTO public.merchant_group_merchants
    (merchant_group_entity_id, merchants_id)
VALUES (1, 1),
       (2, 3);


/*-------------------------------------menu-----------------------------------------*/
INSERT INTO public.menu
    (id, description, menu_type, "name", merchant_id, main_image_id)
values (1, 'main kitchen', 'MAIN_KITCHEN', 'Main kitchen', 1, 4),
       (2, 'main kitchen', 'MAIN_KITCHEN', 'Main kitchen', 2, 5),
       (3, 'main kitchen', 'MAIN_KITCHEN', 'Main kitchen', 3, 6);


/*-------------------------------------kitchen-----------------------------------------*/
INSERT INTO public.kitchen
    (id, description, "name", "type", image_id, menu_id)
values (1, 'pizza', 'pizza', 'ITALIANO', 7, 1),
       (2, 'salads', 'salads', 'VEGETARIAN', 8, 1),
       (3, 'buckets', 'buckets', 'FAST_FOOD', 9, 2),
       (4, 'burgers', 'burgers', 'FAST_FOOD', 10, 2),
       (5, 'garnish', 'garnish', 'UKRAIN', 11, 3),
       (6, 'first courses', 'first courses', 'UKRAIN', 12, 3);


/*-------------------------------------product groups-----------------------------------------*/
INSERT INTO public.product_group
    (id, description, "name", main_image_id)
VALUES (1, 'fast food', 'fast food', 27),
       (2, 'pizza', 'pizza', 28);


/*-------------------------------------product-----------------------------------------*/
INSERT INTO public.product
(id, description, "name", price, discount_id, kitchen_id, main_image_id)
VALUES (1, 'four meats pizza', 'four meats pizza', 230, null, 1, 13),
       (2, 'five cheeses pizza', 'five cheeses pizza', 254, null, 1, 14),
       (3, 'caesar', 'caesar', 123, null, 2, 15),
       (4, 'seafood salad', 'seafood salad', 567, null, 2, 16),
       (5, 'discovery bucket', 'discovery bucket', 654, null, 3, 17),
       (6, 'sanders bucket', 'sanders bucket', 211, null, 3, 18),
       (7, 'cheeseburger', 'cheeseburger', 654, null, 4, 19),
       (8, 'burger', 'burger', 112, null, 4, 20),
       (9, 'buckwheat', 'buckwheat', 456, null, 5, 21),
       (10, 'ragout', 'ragout', 333, null, 5, 22),
       (11, 'brine', 'brine', 222, null, 6, 23),
       (12, 'borsch', 'borsch', 112, null, 6, 24);
/*-----kitchen products-------*/
INSERT INTO public.kitchen_items
    (kitchen_entity_id, items_id)
VALUES (1, 1),
       (1, 2),
       (2, 3),
       (2, 4),
       (3, 5),
       (3, 6),
       (4, 7),
       (4, 8),
       (5, 9),
       (5, 10),
       (6, 11),
       (6, 12);
/*-----product groups-------*/
INSERT INTO public.product_group_products
    (product_group_entity_id, products_id)
VALUES (1, 5),
       (1, 6),
       (1, 7),
       (1, 8),
       (2, 1),
       (2, 2);


/*-------------------------------------waiters-----------------------------------------*/
INSERT INTO public.waiter
    (id, description, "name", status, image_id, user_id)
VALUES (1, 'Roma Ruccolla', 'Roma Ruccolla', 'FREE', 29, 4),
       (2, 'Vitya Ruccolla', 'Vitya Ruccolla', 'FREE', 30, 5),
       (3, 'Roma kfc', 'Roma kfc', 'FREE', 31, 6),
       (4, 'Vitya kfc', 'Vitya kfc', 'FREE', 32, 7),
       (5, 'Roma puz hata', 'Roma puz hata', 'FREE', 33, 8),
       (6, 'Vitya puz hata', 'Vitya puz hata', 'FREE', 34, 9);
/*-----merchant waiters-------*/
INSERT INTO public.merchant_waiters
    (merchant_entity_id, waiters_id)
VALUES (1, 1),
       (1, 2),
       (2, 3),
       (2, 4),
       (3, 5),
       (3, 6);


/*-------------------------------------places-----------------------------------------*/
INSERT INTO public.place
    (id, description, "name", status)
VALUES (1, 'at window', 'window', 'FREE'),
       (2, 'at bar', 'bar', 'FREE'),
       (3, 'at window', 'window', 'FREE'),
       (4, 'at bar', 'bar', 'FREE'),
       (5, 'at window', 'window', 'FREE'),
       (6, 'at bar', 'bar', 'FREE'),
       (7, 'at window', 'window', 'FREE'),
       (8, 'at bar', 'bar', 'FREE'),
       (9, 'at window', 'window', 'FREE'),
       (10, 'at bar', 'bar', 'FREE'),
       (11, 'at window', 'window', 'FREE'),
       (12, 'at bar', 'bar', 'FREE');
/*-----waiter places-------*/
INSERT INTO public.place_waiters
    (place_entity_id, waiters_id)
VALUES (1, 1),
       (2, 1),
       (3, 2),
       (4, 2),
       (5, 3),
       (6, 3),
       (7, 4),
       (8, 4),
       (9, 5),
       (10, 5),
       (11, 6),
       (12, 6);

















