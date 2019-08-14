package net.praqma.codeacademy.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {

    @Test
    public void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    public void old_brie() {
        Item[] items = new Item[] { new Item("Aged Brie", 0, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertTrue("brie is a higher quality",app.items[0].quality>1 );
        
    }

    @Test
    public void never_over_50() {
        Item[] items = new Item[] { new Item("Aged Brie", 0, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertTrue("brie is higher quality than it should be able to",app.items[0].quality==50 );
        
    }

    @Test
    public void never_negative() {
        Item[] items = new Item[] { new Item("TestUnder0", 1, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertTrue("Item below 0", app.items[0].quality >= 0);
    }

    @Test
    public void double_after_sellByDate() {
        Item[] items = new Item[] { new Item("PastDate", -1, 45)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertTrue("Item does not degrade twice as fast after passing sell by date", app.items[0].quality==43);
    }
    
    @Test 
    public void sulfuras_never_change() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 6, 45)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertTrue("Sulfuras has changed, when not supposed to.", (items[0].quality == 45 && items[0].sellIn == 6));
    }

    @Test
    public void backstage_pass_increase() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 15, 40), 
        new Item("Backstage passes to a TAFKAL80ETC concert", 10, 40), 
        new Item("Backstage passes to a TAFKAL80ETC concert", 5, 40),
        new Item("Backstage passes to a TAFKAL80ETC concert", -1, 40)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertTrue("Backstage pass 1 is gaining quality incorrectly. Quality is: " + items[0].quality, items[0].quality == 41);
        assertTrue("Backstage pass 2 is gaining quality incorrectly. Quality is: " + items[1].quality, items[1].quality == 42);
        assertTrue("Backstage pass 3 is gaining quality incorrectly. Quality is: " + items[2].quality, items[2].quality == 43);
        assertTrue("Backstage pass 4 quality is not null. Quality is: " + items[3].quality, items[3].quality == 0);
    }

    @Test
    public void conjured_items_degrade_twice() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 5, 40)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertTrue("Conjured item is not losing quality twice as fast", items[0].quality == 38);
    }
}
