package com.eleks.academy.pharmagator.dataproviders;

import com.eleks.academy.pharmagator.entities.Pharmacy;

/**
 * The implementations of this interface returns instance of {@link Pharmacy}
 * so we could save data properly in our scheduler
 *
 * @see com.eleks.academy.pharmagator.scheduler.Scheduler
 */
public interface PharmacyDataProvider extends DataProvider {

    Pharmacy getPharmacy();
}
