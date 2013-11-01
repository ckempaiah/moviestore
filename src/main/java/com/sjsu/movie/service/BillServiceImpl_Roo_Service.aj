// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.movie.service;

import com.sjsu.movie.domain.Bill;
import com.sjsu.movie.service.BillServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect BillServiceImpl_Roo_Service {
    
    declare @type: BillServiceImpl: @Service;
    
    declare @type: BillServiceImpl: @Transactional;
    
    public long BillServiceImpl.countAllBills() {
        return Bill.countBills();
    }
    
    public void BillServiceImpl.deleteBill(Bill bill) {
        bill.remove();
    }
    
    public Bill BillServiceImpl.findBill(Long id) {
        return Bill.findBill(id);
    }
    
    public List<Bill> BillServiceImpl.findAllBills() {
        return Bill.findAllBills();
    }
    
    public List<Bill> BillServiceImpl.findBillEntries(int firstResult, int maxResults) {
        return Bill.findBillEntries(firstResult, maxResults);
    }
    
    public void BillServiceImpl.saveBill(Bill bill) {
        bill.persist();
    }
    
    public Bill BillServiceImpl.updateBill(Bill bill) {
        return bill.merge();
    }
    
}
