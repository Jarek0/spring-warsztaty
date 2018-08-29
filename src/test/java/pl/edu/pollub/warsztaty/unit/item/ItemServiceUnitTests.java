package pl.edu.pollub.warsztaty.unit.item;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.edu.pollub.warsztaty.bid.dao.BidDao;
import pl.edu.pollub.warsztaty.bid.domain.BidEntity;
import pl.edu.pollub.warsztaty.bid.exception.BidAmountLesserThanMax;
import pl.edu.pollub.warsztaty.item.dao.ItemDao;
import pl.edu.pollub.warsztaty.item.domain.ItemEntity;
import pl.edu.pollub.warsztaty.item.exception.ItemNotFoundException;
import pl.edu.pollub.warsztaty.item.service.ItemService;
import pl.edu.pollub.warsztaty.item.service.ItemServiceImpl;
import pl.edu.pollub.warsztaty.item.validator.BidValidator;
import pl.edu.pollub.warsztaty.item.validator.ItemValidator;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceUnitTests {

    @Mock
    private ItemDao itemDao;

    @Mock
    private BidDao bidDao;

    private ItemService itemService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void init() {
        BidValidator bidValidator = new BidValidator(bidDao);
        ItemValidator itemValidator = new ItemValidator(itemDao);
        itemService = new ItemServiceImpl(bidValidator, itemValidator, itemDao);
    }

    @Test
    public void shouldPlaceBid() {
        //given
        Long itemId = 1L;
        BigDecimal newBidAmount = BigDecimal.valueOf(500);

        Optional<ItemEntity> fountItem = Optional.of(new ItemEntity("item1", "category1"));
        when(itemDao.findById(itemId)).thenReturn(fountItem);
        when(bidDao.findMaxBidForItem(itemId)).thenReturn(BigDecimal.valueOf(400));
        when(itemDao.save(fountItem.get())).thenAnswer(AdditionalAnswers.returnsFirstArg());

        //when
        BidEntity newBid = itemService.placeBid(itemId, newBidAmount);

        //then
        assertEquals(newBid.getAmount(), newBidAmount);
    }

    @Test
    public void shouldNotPlaceBidLesserThanMaximalBidForItem() {
        //given
        Long itemId = 1L;
        BigDecimal newBidAmount = BigDecimal.valueOf(500);

        Optional<ItemEntity> fountItem = Optional.of(new ItemEntity("item1", "category1"));
        when(itemDao.findById(itemId)).thenReturn(fountItem);
        when(bidDao.findMaxBidForItem(itemId)).thenReturn(BigDecimal.valueOf(600));

        //expected
        thrown.expect(BidAmountLesserThanMax.class);
        thrown.expectMessage("New bid has amount: 500 lesser than max bid amount: 600 for item with id: 1");

        //when
        itemService.placeBid(itemId, newBidAmount);
    }

    @Test(expected = ItemNotFoundException.class)
    public void shouldNotPlaceBidForNotExistingItem() {
        //given
        Long itemId = 1L;
        BigDecimal newBidAmount = BigDecimal.valueOf(500);

        when(itemDao.findById(itemId)).thenReturn(Optional.empty());

        //when
        itemService.placeBid(itemId, newBidAmount);
    }

    @Test
    public void shouldCreateItem() {
        //given
        ItemEntity itemToCreate = new ItemEntity("item1", "category1");

        Optional<ItemEntity> foundItem = Optional.empty();
        when(itemDao.findByName(itemToCreate.getName())).thenReturn(foundItem);
        when(itemDao.getItemsCountForCategory(itemToCreate.getCategory())).thenReturn(3);
        when(itemDao.save(itemToCreate)).thenAnswer(AdditionalAnswers.returnsFirstArg());

        //when
        ItemEntity createdItem = itemService.createItem(itemToCreate);

        assertNotNull(createdItem);
        assertEquals("item1", createdItem.getName());
        assertEquals("category1", createdItem.getCategory());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateItemWithNotUniqueName() {
        //given
        ItemEntity itemToCreate = new ItemEntity("item1", "category1");

        Optional<ItemEntity> foundItem = Optional.of(new ItemEntity("item1", "category2"));
        when(itemDao.findByName(itemToCreate.getName())).thenReturn(foundItem);

        //when
        ItemEntity createdItem = itemService.createItem(itemToCreate);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateItemWithCategoryWhichHasFourItems() {
        //given
        ItemEntity itemToCreate = new ItemEntity("item1", "category1");

        Optional<ItemEntity> foundItem = Optional.empty();
        when(itemDao.findByName(itemToCreate.getName())).thenReturn(foundItem);
        when(itemDao.getItemsCountForCategory(itemToCreate.getCategory())).thenReturn(4);

        //when
        ItemEntity createdItem = itemService.createItem(itemToCreate);
    }
}
