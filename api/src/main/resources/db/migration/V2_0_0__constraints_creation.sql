ALTER TABLE prices
    ADD CONSTRAINT prices_medicines
        FOREIGN KEY (medicine_id)
            REFERENCES medicines (id)
;

ALTER TABLE prices
    ADD CONSTRAINT prices_pharmacies
        FOREIGN KEY (pharmacy_id)
            REFERENCES pharmacies (id)
;