PGDMP                      |            tourism_agency    16.2    16.2 "    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16439    tourism_agency    DATABASE     �   CREATE DATABASE tourism_agency WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE tourism_agency;
                postgres    false            �            1259    16448    hotel    TABLE       CREATE TABLE public.hotel (
    hotel_id integer NOT NULL,
    hotel_name text NOT NULL,
    hotel_city text NOT NULL,
    hotel_state text NOT NULL,
    hotel_address text NOT NULL,
    hotel_mail text NOT NULL,
    hotel_phone text NOT NULL,
    hotel_star text NOT NULL,
    hotel_fitness boolean NOT NULL,
    hotel_concierge boolean NOT NULL,
    hotel_spa boolean NOT NULL,
    hotel_room_service boolean NOT NULL,
    hotel_pool boolean NOT NULL,
    hotel_wifi boolean NOT NULL,
    hotel_car_park boolean NOT NULL
);
    DROP TABLE public.hotel;
       public         heap    postgres    false            �            1259    16469    hotels_hotel_id_seq    SEQUENCE     �   ALTER TABLE public.hotel ALTER COLUMN hotel_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hotels_hotel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    217            �            1259    16454    pension    TABLE     �   CREATE TABLE public.pension (
    pension_id integer NOT NULL,
    pension_hotel_id integer NOT NULL,
    pension_type text NOT NULL
);
    DROP TABLE public.pension;
       public         heap    postgres    false            �            1259    16485    pension_pension_id_seq    SEQUENCE     �   ALTER TABLE public.pension ALTER COLUMN pension_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.pension_pension_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    219            �            1259    16460    reservation    TABLE       CREATE TABLE public.reservation (
    reservation_id integer NOT NULL,
    reservation_room_id integer NOT NULL,
    reservation_checkin_date date NOT NULL,
    reservation_checkout_date date NOT NULL,
    reservation_total_price integer NOT NULL,
    reservation_guest_adult_count integer NOT NULL,
    reservation_guest_name text NOT NULL,
    reservation_guest_national_id text NOT NULL,
    reservation_guest_mail text NOT NULL,
    reservation_guest_phone text NOT NULL,
    reservation_guest_child_count integer NOT NULL
);
    DROP TABLE public.reservation;
       public         heap    postgres    false            �            1259    16480    reservation_reservation_id_seq    SEQUENCE     �   ALTER TABLE public.reservation ALTER COLUMN reservation_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.reservation_reservation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    221            �            1259    16457    room    TABLE     8  CREATE TABLE public.room (
    room_id integer NOT NULL,
    room_hotel_id integer NOT NULL,
    room_pension_id integer NOT NULL,
    room_season_id integer NOT NULL,
    room_type text NOT NULL,
    room_stock integer NOT NULL,
    room_adult_price integer NOT NULL,
    room_child_price integer NOT NULL,
    room_bed_capacity integer NOT NULL,
    room_area integer NOT NULL,
    room_television boolean NOT NULL,
    room_minibar boolean NOT NULL,
    room_game_console boolean NOT NULL,
    room_cashbox boolean NOT NULL,
    room_projection boolean NOT NULL
);
    DROP TABLE public.room;
       public         heap    postgres    false            �            1259    16479    room_room_id_seq    SEQUENCE     �   ALTER TABLE public.room ALTER COLUMN room_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.room_room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    220            �            1259    16451    season    TABLE     �   CREATE TABLE public.season (
    season_id integer NOT NULL,
    season_name text NOT NULL,
    season_hotel_id integer NOT NULL,
    season_start_date date NOT NULL,
    season_finish_date date NOT NULL
);
    DROP TABLE public.season;
       public         heap    postgres    false            �            1259    16470    seasons_season_id_seq    SEQUENCE     �   ALTER TABLE public.season ALTER COLUMN season_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.seasons_season_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    218            �            1259    16440    user    TABLE     �   CREATE TABLE public."user" (
    user_id integer NOT NULL,
    user_username text NOT NULL,
    user_password text NOT NULL,
    user_first_name text NOT NULL,
    user_last_name text NOT NULL,
    user_mail text NOT NULL,
    user_role text NOT NULL
);
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    16447    user_user_id_seq    SEQUENCE     �   ALTER TABLE public."user" ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    215            �          0    16448    hotel 
   TABLE DATA           �   COPY public.hotel (hotel_id, hotel_name, hotel_city, hotel_state, hotel_address, hotel_mail, hotel_phone, hotel_star, hotel_fitness, hotel_concierge, hotel_spa, hotel_room_service, hotel_pool, hotel_wifi, hotel_car_park) FROM stdin;
    public          postgres    false    217   5,       �          0    16454    pension 
   TABLE DATA           M   COPY public.pension (pension_id, pension_hotel_id, pension_type) FROM stdin;
    public          postgres    false    219   -       �          0    16460    reservation 
   TABLE DATA           >  COPY public.reservation (reservation_id, reservation_room_id, reservation_checkin_date, reservation_checkout_date, reservation_total_price, reservation_guest_adult_count, reservation_guest_name, reservation_guest_national_id, reservation_guest_mail, reservation_guest_phone, reservation_guest_child_count) FROM stdin;
    public          postgres    false    221   �-       �          0    16457    room 
   TABLE DATA             COPY public.room (room_id, room_hotel_id, room_pension_id, room_season_id, room_type, room_stock, room_adult_price, room_child_price, room_bed_capacity, room_area, room_television, room_minibar, room_game_console, room_cashbox, room_projection) FROM stdin;
    public          postgres    false    220   ".       �          0    16451    season 
   TABLE DATA           p   COPY public.season (season_id, season_name, season_hotel_id, season_start_date, season_finish_date) FROM stdin;
    public          postgres    false    218   �.       �          0    16440    user 
   TABLE DATA           ~   COPY public."user" (user_id, user_username, user_password, user_first_name, user_last_name, user_mail, user_role) FROM stdin;
    public          postgres    false    215   V/       �           0    0    hotels_hotel_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.hotels_hotel_id_seq', 8, true);
          public          postgres    false    222            �           0    0    pension_pension_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.pension_pension_id_seq', 27, true);
          public          postgres    false    226            �           0    0    reservation_reservation_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.reservation_reservation_id_seq', 18, true);
          public          postgres    false    225            �           0    0    room_room_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.room_room_id_seq', 10, true);
          public          postgres    false    224            �           0    0    seasons_season_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.seasons_season_id_seq', 19, true);
          public          postgres    false    223            �           0    0    user_user_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.user_user_id_seq', 7, true);
          public          postgres    false    216            6           2606    16468    hotel hotels_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT hotels_pkey PRIMARY KEY (hotel_id);
 ;   ALTER TABLE ONLY public.hotel DROP CONSTRAINT hotels_pkey;
       public            postgres    false    217            :           2606    16484    pension pension_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.pension
    ADD CONSTRAINT pension_pkey PRIMARY KEY (pension_id);
 >   ALTER TABLE ONLY public.pension DROP CONSTRAINT pension_pkey;
       public            postgres    false    219            >           2606    16478    reservation reservation_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (reservation_id);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pkey;
       public            postgres    false    221            <           2606    16474    room room_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (room_id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    220            8           2606    16464    season seasons_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.season
    ADD CONSTRAINT seasons_pkey PRIMARY KEY (season_id);
 =   ALTER TABLE ONLY public.season DROP CONSTRAINT seasons_pkey;
       public            postgres    false    218            4           2606    16446    user user_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    215            �   �   x�]��J�0E�7_�}��vfᮺD�n��C�<I^�+����i��.�N���%e��M�T���?����9؉�(%F܇��h��p�_�[����Ӟ�DT��V-�j��{��aݶ�2�q]��?��i�����_���J
�g�*Ӳ�<����RO/��U��]�F�w�wv�w�M߹3fG������`      �   u   x�34�4��))JTp��Q��K�)-�,K�24J���*8�&f�%�p�qs�����g��q�E�UY u����x$��E�9M�Zkd�@�ld�i���(��-F��� ?R<      �   �   x�m�]
� ��'w�%J��[�K���mU�~�!N�0�Z�(����\*�$�w������D��Í;�{��M*�rpS�f�z`����G����}���uh���S�eN�B֘���k|��+"��M      �   �   x�e�=�0���� ��owNPV&�U����N�H��9/D���z��<�2�Dr1��ڎa8Py����ڈAC����^�g�_^�s�j5SL�}�����+���}�Y����YJI�m^')攲���f�Ni��I�~w�e.Gc��FO      �   s   x�m�M@0���]H�XZ m�46�mh	�Y�ŗ7�$l�];8�`�*D$���"�7�!tģ��`u5�~��f��T����F;d �#*��4���<'�%"5�,�      �   M   x�3�LL����442�H,��N�tI-�:��R��.��~\F��ũE`�%��%�@V����������� �xW     