package com.sjsu.movie.service;

import com.sjsu.movie.domain.Bill;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BillServiceImpl implements BillService {

	public long countAllBills() {
        return Bill.countBills();
    }

	public void deleteBill(Bill bill) {
        bill.remove();
    }

	public Bill findBill(Long id) {
        return Bill.findBill(id);
    }

	public List<Bill> findAllBills() {
        return Bill.findAllBills();
    }

	public List<Bill> findBillEntries(int firstResult, int maxResults) {
        return Bill.findBillEntries(firstResult, maxResults);
    }

	public void saveBill(Bill bill) {
        bill.persist();
    }

	public Bill updateBill(Bill bill) {
        return bill.merge();
    }
}
