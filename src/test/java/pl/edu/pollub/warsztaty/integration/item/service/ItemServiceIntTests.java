package pl.edu.pollub.warsztaty.integration.item.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pollub.warsztaty.bid.dao.BidDao;
import pl.edu.pollub.warsztaty.bid.domain.BidEntity;
import pl.edu.pollub.warsztaty.bid.exception.BidAmountLesserThanMax;
import pl.edu.pollub.warsztaty.item.dao.ItemDao;
import pl.edu.pollub.warsztaty.item.domain.ItemEntity;
import pl.edu.pollub.warsztaty.item.service.ItemService;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemServiceIntTests {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private BidDao bidDao;

    @Autowired
    private ItemService itemService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void cleanDb() {
        itemDao.deleteAll();
        bidDao.deleteAll();
    }

    @Test
    @Transactional
    public void shouldPlaceBid() {
        //given
        BigDecimal newBidAmount = BigDecimal.valueOf(500.00);
        ItemEntity testItem = new ItemEntity("item1", "category1");
        testItem.addBids(200.0, 300.0, 400.0);
        testItem = itemDao.save(testItem);

        //when
        BidEntity newBid = itemService.placeBid(testItem.getId(), newBidAmount);

        //then
        assertEquals(newBid.getAmount(), newBidAmount);

        Optional<ItemEntity> maybeItemWithPlacedNewBid = itemDao.findById(testItem.getId());

        assertTrue(maybeItemWithPlacedNewBid.isPresent());
        assertEquals(4, maybeItemWithPlacedNewBid.get().getBids().size());
        assertEquals(0, newBidAmount.compareTo(bidDao.findMaxBidForItem(testItem.getId())));
    }

    @Test
    public void shouldNotPlaceBidLesserThanMaximalBidForItem() {
        //given
        BigDecimal newBidAmount = BigDecimal.valueOf(350);
        ItemEntity testItem = new ItemEntity("item1", "category1");
        testItem.addBids(200.0, 300.0, 400.0);
        testItem = itemDao.save(testItem);

        //expected
        thrown.expect(BidAmountLesserThanMax.class);
        thrown.expectMessage("New bid has amount: 350 lesser than max bid amount: 400.00 for item with id: " + testItem.getId());

        //when
        itemService.placeBid(testItem.getId(), newBidAmount);
    }

    @Test
    public void shouldCreateNewItemAndPlaceBid(){
        //given
        ItemEntity itemToCreate = new ItemEntity("item1", "category1");

        BigDecimal newBidAmount = BigDecimal.valueOf(350.00);

        //when
        ItemEntity item = itemService.createItem(itemToCreate);
        assertNotNull(item);
        assertEquals("item1", itemToCreate.getName());

        BidEntity newBid = itemService.placeBid(item.getId(), newBidAmount);

        //then
        assertEquals(newBid.getAmount(), newBidAmount);
        assertEquals(0, newBidAmount.compareTo(bidDao.findMaxBidForItem(item.getId())));
    }
}
