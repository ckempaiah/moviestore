package com.sjsu.movie.service;
import com.sjsu.movie.domain.Bill;
import java.util.List;

public interface BillService {

	public abstract long countAllBills();


	public abstract void deleteBill(Bill bill);


	public abstract Bill findBill(Long id);


	public abstract List<Bill> findAllBills();


	public abstract List<Bill> findBillEntries(int firstResult, int maxResults);


	public abstract void saveBill(Bill bill);


	public abstract Bill updateBill(Bill bill);

}
