ALTER TABLE prices
    ADD CONSTRAINT prices_medicines
        FOREIGN KEY (medicine_id)
            REFERENCES medicines (id) NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

ALTER TABLE prices
    ADD CONSTRAINT prices_pharmacies
        FOREIGN KEY (pharmacy_id)
            REFERENCES pharmacies (id) INITIALLY IMMEDIATE
;