package pokerBase;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.DeckException;
import exceptions.HandException;
import pokerEnums.eCardNo;
import pokerEnums.eHandStrength;
import pokerEnums.eRank;
import pokerEnums.eSuit;

public class HandTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	private Hand SetHand(ArrayList<Card> setCards, Hand h)
	{
		Object t = null;
		
		try {
			//	Load the Class into 'c'
			Class<?> c = Class.forName("pokerBase.Hand");
			//	Create a new instance 't' from the no-arg Deck constructor
			t = c.newInstance();
			//	Load 'msetCardsInHand' with the 'Draw' method (no args);
			Method msetCardsInHand = c.getDeclaredMethod("setCardsInHand", new Class[]{ArrayList.class});
			//	Change the visibility of 'setCardsInHand' to true *Good Grief!*
			msetCardsInHand.setAccessible(true);
			//	invoke 'msetCardsInHand'
			Object oDraw = msetCardsInHand.invoke(t, setCards);
			
		} catch (ClassNotFoundException x) {
			x.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		h = (Hand)t;
		return h;
		
	}
	/**
	 * This test checks to see if a HandException is throw if the hand only has two cards.
	 * @throws Exception
	 */
	@Test(expected = HandException.class)
	public void TestEvalShortHand() throws Exception {
		
		Hand h = new Hand();
		
		ArrayList<Card> ShortHand = new ArrayList<Card>();
		ShortHand.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		ShortHand.add(new Card(eSuit.CLUBS,eRank.ACE,0));

		h = SetHand(ShortHand,h);
		
		//	This statement should throw a HandException
		h = Hand.EvaluateHand(h);	
	}	
		

	@Test
	public void TestRoyalFlush() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> RoyalFlush = new ArrayList<Card>();
		RoyalFlush.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		RoyalFlush.add(new Card(eSuit.CLUBS,eRank.KING,0));
		RoyalFlush.add(new Card(eSuit.CLUBS,eRank.QUEEN,0));		
		RoyalFlush.add(new Card(eSuit.CLUBS,eRank.JACK,0));
		RoyalFlush.add(new Card(eSuit.CLUBS,eRank.TEN,0));
		
		Hand h = new Hand();
		h = SetHand(RoyalFlush,h);
		
		boolean bActualIsHandRoyalFlush = Hand.isHandRoyalFlush(h, hs);
		boolean bExpectedIsHandRoyalFlush = true;
		
		assertEquals(bActualIsHandRoyalFlush,bExpectedIsHandRoyalFlush);		
		assertEquals(hs.getHiHand(),eRank.ACE.getiRankNbr());		
		/*
		 * No kickers
		 */
	}
	
	@Test
	public void TestRoyalFlushEval() {
		
		ArrayList<Card> RoyalFlush = new ArrayList<Card>();
		RoyalFlush.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		RoyalFlush.add(new Card(eSuit.CLUBS,eRank.KING,0));
		RoyalFlush.add(new Card(eSuit.CLUBS,eRank.QUEEN,0));		
		RoyalFlush.add(new Card(eSuit.CLUBS,eRank.JACK,0));
		RoyalFlush.add(new Card(eSuit.CLUBS,eRank.TEN,0));
		
		
		
		Hand h = new Hand();
		h = SetHand(RoyalFlush,h);
		
		try {
			h = Hand.EvaluateHand(h);
		} catch (HandException e) {			
			e.printStackTrace();
			fail("TestROyalFlush failed");
		}
		HandScore hs = h.getHandScore();
		boolean bActualIsHandRoyalFlush = Hand.isHandRoyalFlush(h, hs);
		boolean bExpectedIsHandRoyalFlush = true;
		
		assertEquals(bActualIsHandRoyalFlush,bExpectedIsHandRoyalFlush);		
		assertEquals(hs.getHiHand(),eRank.ACE.getiRankNbr());			
		/*
		 * No kickers
		 */
	}	
	
	
	
	
	@Test
	public void TestStraightFlush() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> StraightFlush = new ArrayList<Card>();
		StraightFlush.add(new Card(eSuit.CLUBS,eRank.SIX,0));
		StraightFlush.add(new Card(eSuit.CLUBS,eRank.FIVE,0));
		StraightFlush.add(new Card(eSuit.CLUBS,eRank.FOUR,0));		
		StraightFlush.add(new Card(eSuit.CLUBS,eRank.THREE,0));
		StraightFlush.add(new Card(eSuit.CLUBS,eRank.TWO,0));
		
		Hand h = new Hand();
		h = SetHand(StraightFlush,h);
		
		boolean bActualIsHandStraightFlush = Hand.isHandStraightFlush(h, hs);
		boolean bExpectedIsHandStraightFlush = true;
		
		assertEquals(bActualIsHandStraightFlush,bExpectedIsHandStraightFlush);		
		assertEquals(hs.getHiHand(),eRank.SIX.getiRankNbr());		
		/*
		 * No kickers
		 */
	}
	
	@Test
	public void TestStrightFlushEval() {
		
		ArrayList<Card> StraightFlush = new ArrayList<Card>();
		StraightFlush.add(new Card(eSuit.CLUBS,eRank.SIX,0));
		StraightFlush.add(new Card(eSuit.CLUBS,eRank.FIVE,0));
		StraightFlush.add(new Card(eSuit.CLUBS,eRank.FOUR,0));		
		StraightFlush.add(new Card(eSuit.CLUBS,eRank.THREE,0));
		StraightFlush.add(new Card(eSuit.CLUBS,eRank.TWO,0));
		
		
		Hand h = new Hand();
		h = SetHand(StraightFlush,h);
		
		try {
			h = Hand.EvaluateHand(h);
		} catch (HandException e) {			
			e.printStackTrace();
			fail("TestStraightFlush failed");
		}
		HandScore hs = h.getHandScore();
		boolean bActualIsHandStraightFlush = Hand.isHandStraightFlush(h, hs);
		boolean bExpectedIsHandStraightFlush = true;
		
		assertEquals(bActualIsHandStraightFlush,bExpectedIsHandStraightFlush);		
		assertEquals(hs.getHiHand(),eRank.SIX.getiRankNbr());	
		/*
		 * No kickers
		 */
	}	
	
	
	
	
	
	
	@Test
	public void TestFourOfAKind() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> FourOfAKind = new ArrayList<Card>();
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));		
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.KING,0));
		
		Hand h = new Hand();
		h = SetHand(FourOfAKind,h);
		
		boolean bActualIsHandFourOfAKind = Hand.isHandFourOfAKind(h, hs);
		boolean bExpectedIsHandFourOfAKind = true;
		
		//	Did this evaluate to Four of a Kind?
		assertEquals(bActualIsHandFourOfAKind,bExpectedIsHandFourOfAKind);		
		//	Was the four of a kind an Ace?
		assertEquals(hs.getHiHand(),eRank.ACE.getiRankNbr());		
		//	FOAK has one kicker.  Was it a Club?
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.CLUBS);
		//	FOAK has one kicker.  Was it a King?		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.KING);
	}
	
	@Test
	public void TestFourOfAKindEval() {
		
		ArrayList<Card> FourOfAKind = new ArrayList<Card>();
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.KING,0));
		
		Hand h = new Hand();
		h = SetHand(FourOfAKind,h);
		
		try {
			h = Hand.EvaluateHand(h);
		} catch (HandException e) {			
			e.printStackTrace();
			fail("TestFourOfAKindEval failed");
		}
		HandScore hs = h.getHandScore();
		boolean bActualIsHandFourOfAKind = Hand.isHandFourOfAKind(h, hs);
		boolean bExpectedIsHandFourOfAKind = true;
		
		//	Did this evaluate to Four of a Kind?
		assertEquals(bActualIsHandFourOfAKind,bExpectedIsHandFourOfAKind);		
		//	Was the four of a kind an Ace?
		assertEquals(hs.getHiHand(),eRank.ACE.getiRankNbr());		
		//	FOAK has one kicker.  Was it a Club?
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.CLUBS);
		//	FOAK has one kicker.  Was it a King?		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.KING);
	}	

	@Test	
	public void TestFullHouse() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> FullHouse = new ArrayList<Card>();
		FullHouse.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FullHouse.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FullHouse.add(new Card(eSuit.SPADES,eRank.TWO,0));		
		FullHouse.add(new Card(eSuit.HEARTS,eRank.TWO,0));
		FullHouse.add(new Card(eSuit.DIAMONDS,eRank.TWO,0));
		
		Hand h = new Hand();
		h = SetHand(FullHouse,h);
		
		boolean bActualIsHandFullHouse = Hand.isHandFullHouse(h, hs);
		boolean bExpectedIsHandFullHouse = true;
		
		assertEquals(bActualIsHandFullHouse,bExpectedIsHandFullHouse);		
		assertEquals(hs.getHiHand(),eRank.ACE.getiRankNbr());		
		/*
		 * No kickers
		 * assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.CLUBS);		
		 * assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.KING);
		 */
	}
	
	@Test
	public void TestFullHouseEval() {
		
		ArrayList<Card> FullHouse = new ArrayList<Card>();
		FullHouse.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FullHouse.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FullHouse.add(new Card(eSuit.SPADES,eRank.TWO,0));
		FullHouse.add(new Card(eSuit.HEARTS,eRank.TWO,0));
		FullHouse.add(new Card(eSuit.DIAMONDS,eRank.TWO,0));
		
		Hand h = new Hand();
		h = SetHand(FullHouse,h);
		
		try {
			h = Hand.EvaluateHand(h);
		} catch (HandException e) {			
			e.printStackTrace();
			fail("TestFullHouse failed");
		}
		HandScore hs = h.getHandScore();
		boolean bActualIsFullHouse = Hand.isHandFullHouse(h, hs);
		boolean bExpectedIsHandFullHouse = true;
		
		assertEquals(bActualIsFullHouse,bExpectedIsHandFullHouse);		
		assertEquals(hs.getHiHand(),eRank.ACE.getiRankNbr());		
		/*
		 * No kickers
		 */
	}	

	@Test
	public void TestFlush() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> Flush = new ArrayList<Card>();
		Flush.add(new Card(eSuit.CLUBS,eRank.SIX,0));
		Flush.add(new Card(eSuit.CLUBS,eRank.FIVE,0));
		Flush.add(new Card(eSuit.CLUBS,eRank.FIVE,0));		
		Flush.add(new Card(eSuit.CLUBS,eRank.THREE,0));
		Flush.add(new Card(eSuit.CLUBS,eRank.TWO,0));
		
		Hand h = new Hand();
		h = SetHand(Flush,h);
		
		boolean bActualIsHandFlush = Hand.isHandFlush(h, hs);
		boolean bExpectedIsHandFlush = true;
		
		assertEquals(bActualIsHandFlush,bExpectedIsHandFlush);		
		assertEquals(hs.getHiHand(),eRank.SIX.getiRankNbr());		
		/*
		 * No kickers
		 */
	}
	
	@Test
	public void TestFlushEval() {
		
		ArrayList<Card> Flush = new ArrayList<Card>();
		Flush.add(new Card(eSuit.CLUBS,eRank.SIX,0));
		Flush.add(new Card(eSuit.CLUBS,eRank.FIVE,0));
		Flush.add(new Card(eSuit.CLUBS,eRank.FIVE,0));		
		Flush.add(new Card(eSuit.CLUBS,eRank.THREE,0));
		Flush.add(new Card(eSuit.CLUBS,eRank.TWO,0));
		
		Hand h = new Hand();
		h = SetHand(Flush,h);
		
		try {
			h = Hand.EvaluateHand(h);
		} catch (HandException e) {			
			e.printStackTrace();
			fail("TestFlush failed");
		}
		HandScore hs = h.getHandScore();
		boolean bActualIsHandFlush = Hand.isHandFlush(h, hs);
		boolean bExpectedIsHandFlush = true;
		
		assertEquals(bActualIsHandFlush,bExpectedIsHandFlush);		
		assertEquals(hs.getHiHand(),eRank.SIX.getiRankNbr());	
		/*
		 * No kickers
		 */
	}	
	
	
	@Test
	public void TestStraight() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> Straight = new ArrayList<Card>();
		Straight.add(new Card(eSuit.CLUBS,eRank.SIX,0));
		Straight.add(new Card(eSuit.CLUBS,eRank.FIVE,0));
		Straight.add(new Card(eSuit.SPADES,eRank.FOUR,0));		
		Straight.add(new Card(eSuit.HEARTS,eRank.THREE,0));
		Straight.add(new Card(eSuit.DIAMONDS,eRank.TWO,0));
		
		Hand h = new Hand();
		h = SetHand(Straight,h);
		
		boolean bActualIsHandStraight = Hand.isHandStraight(h, hs);
		boolean bExpectedIsHandStraight = true;
		
		assertEquals(bActualIsHandStraight,bExpectedIsHandStraight);		
		assertEquals(hs.getHiHand(),eRank.SIX.getiRankNbr());		
		/*
		 * No kickers
		 */
	}
	
	@Test
	public void TestStraightEval() {
		
		ArrayList<Card> Straight = new ArrayList<Card>();
		Straight.add(new Card(eSuit.CLUBS,eRank.SIX,0));
		Straight.add(new Card(eSuit.CLUBS,eRank.FIVE,0));
		Straight.add(new Card(eSuit.SPADES,eRank.FOUR,0));		
		Straight.add(new Card(eSuit.HEARTS,eRank.THREE,0));
		Straight.add(new Card(eSuit.DIAMONDS,eRank.TWO,0));
		
		Hand h = new Hand();
		h = SetHand(Straight,h);
		
		try {
			h = Hand.EvaluateHand(h);
		} catch (HandException e) {			
			e.printStackTrace();
			fail("TestStraight failed");
		}
		HandScore hs = h.getHandScore();
		boolean bActualIsStraight = Hand.isHandStraight(h, hs);
		boolean bExpectedIsHandStraight = true;
		
		assertEquals(bActualIsStraight,bExpectedIsHandStraight);		
		assertEquals(hs.getHiHand(),eRank.SIX.getiRankNbr());		
		/*
		 * No kickers
		 */
	}	
	
	@Test
	public void TestThreeOfAKind() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> ThreeOfAKind = new ArrayList<Card>();
		ThreeOfAKind.add(new Card(eSuit.HEARTS,eRank.ACE,0));
		ThreeOfAKind.add(new Card(eSuit.HEARTS,eRank.ACE,0));
		ThreeOfAKind.add(new Card(eSuit.HEARTS,eRank.ACE,0));		
		ThreeOfAKind.add(new Card(eSuit.CLUBS,eRank.FOUR,0));
		ThreeOfAKind.add(new Card(eSuit.DIAMONDS,eRank.TWO,0));
		
		Hand h = new Hand();
		h = SetHand(ThreeOfAKind,h);
		
		boolean bActualIsHandThreeOfAKind = Hand.isHandThreeOfAKind(h, hs);
		boolean bExpectedIsHandThreeOfAKind = true;
		
		assertEquals(bActualIsHandThreeOfAKind,bExpectedIsHandThreeOfAKind);		
		assertEquals(hs.getHiHand(),eRank.ACE.getiRankNbr());		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.CLUBS);		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.FOUR);
		assertEquals(hs.getKickers().get(eCardNo.SecondCard.getCardNo()).geteSuit(), eSuit.DIAMONDS);		
		assertEquals(hs.getKickers().get(eCardNo.SecondCard.getCardNo()).geteRank(), eRank.TWO);
		
	}
	
	@Test
	public void TestThreeOfAKindEval() {
		
		ArrayList<Card> ThreeOfAKind = new ArrayList<Card>();
		ThreeOfAKind.add(new Card(eSuit.HEARTS,eRank.ACE,0));
		ThreeOfAKind.add(new Card(eSuit.HEARTS,eRank.ACE,0));
		ThreeOfAKind.add(new Card(eSuit.HEARTS,eRank.ACE,0));		
		ThreeOfAKind.add(new Card(eSuit.CLUBS,eRank.FOUR,0));
		ThreeOfAKind.add(new Card(eSuit.DIAMONDS,eRank.TWO,0));
		
		Hand h = new Hand();
		h = SetHand(ThreeOfAKind,h);
		
		try {
			h = Hand.EvaluateHand(h);
		} catch (HandException e) {			
			e.printStackTrace();
			fail("TestThreeOfAKind failed");
		}
		HandScore hs = h.getHandScore();
		boolean bActualIsThreeOfAKind = Hand.isHandThreeOfAKind(h, hs);
		boolean bExpectedIsHandThreeOfAKind = true;
		
		assertEquals(bActualIsThreeOfAKind,bExpectedIsHandThreeOfAKind);		
		assertEquals(hs.getHiHand(),eRank.ACE.getiRankNbr());		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.CLUBS);		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.FOUR);
		assertEquals(hs.getKickers().get(eCardNo.SecondCard.getCardNo()).geteSuit(), eSuit.DIAMONDS);		
		assertEquals(hs.getKickers().get(eCardNo.SecondCard.getCardNo()).geteRank(), eRank.TWO);
		
		
	}	
	
	@Test
	public void TestTwoPair() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> TwoPair = new ArrayList<Card>();
		TwoPair.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		TwoPair.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		TwoPair.add(new Card(eSuit.SPADES,eRank.FOUR,0));		
		TwoPair.add(new Card(eSuit.HEARTS,eRank.FOUR,0));
		TwoPair.add(new Card(eSuit.DIAMONDS,eRank.TWO,0));
		
		Hand h = new Hand();
		h = SetHand(TwoPair,h);
		
		boolean bActualIsHandTwoPair = Hand.isHandTwoPair(h, hs);
		boolean bExpectedIsHandTwoPair = true;
		
		assertEquals(bActualIsHandTwoPair,bExpectedIsHandTwoPair);		
		assertEquals(hs.getHiHand(),eRank.ACE.getiRankNbr());		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.DIAMONDS);		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.TWO);
	}
	
	@Test
	public void TestTwoPairEval() {
		
		ArrayList<Card> TwoPair = new ArrayList<Card>();
		TwoPair.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		TwoPair.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		TwoPair.add(new Card(eSuit.SPADES,eRank.FOUR,0));		
		TwoPair.add(new Card(eSuit.HEARTS,eRank.FOUR,0));
		TwoPair.add(new Card(eSuit.DIAMONDS,eRank.TWO,0));
		
		Hand h = new Hand();
		h = SetHand(TwoPair,h);
		
		try {
			h = Hand.EvaluateHand(h);
		} catch (HandException e) {			
			e.printStackTrace();
			fail("TestTwoPair failed");
		}
		HandScore hs = h.getHandScore();
		boolean bActualIsFullHouse = Hand.isHandFullHouse(h, hs);
		boolean bExpectedIsHandFullHouse = true;
		
		assertEquals(bActualIsFullHouse,bExpectedIsHandFullHouse);		
		assertEquals(hs.getHiHand(),eRank.ACE.getiRankNbr());		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.DIAMONDS);		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.TWO);

	}	
	
	@Test
	public void TestPair() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> Pair = new ArrayList<Card>();
		Pair.add(new Card(eSuit.CLUBS,eRank.FIVE,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.FIVE,0));
		Pair.add(new Card(eSuit.SPADES,eRank.FOUR,0));		
		Pair.add(new Card(eSuit.HEARTS,eRank.THREE,0));
		Pair.add(new Card(eSuit.DIAMONDS,eRank.TWO,0));
		
		Hand h = new Hand();
		h = SetHand(Pair,h);
		
		boolean bActualIsHandPair = Hand.isHandPair(h, hs);
		boolean bExpectedIsHandPair = true;
		
		assertEquals(bActualIsHandPair,bExpectedIsHandPair);		
		assertEquals(hs.getHiHand(),eRank.FIVE.getiRankNbr());		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.SPADES);		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.FOUR);
		assertEquals(hs.getKickers().get(eCardNo.SecondCard.getCardNo()).geteSuit(), eSuit.HEARTS);		
		assertEquals(hs.getKickers().get(eCardNo.SecondCard.getCardNo()).geteRank(), eRank.THREE);
		assertEquals(hs.getKickers().get(eCardNo.ThirdCard.getCardNo()).geteSuit(), eSuit.DIAMONDS);		
		assertEquals(hs.getKickers().get(eCardNo.ThirdCard.getCardNo()).geteRank(), eRank.TWO);
	}
	
	@Test
	public void TestPairEval() {
		
		ArrayList<Card> Pair = new ArrayList<Card>();
		Pair.add(new Card(eSuit.CLUBS,eRank.FIVE,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.FIVE,0));
		Pair.add(new Card(eSuit.SPADES,eRank.FOUR,0));		
		Pair.add(new Card(eSuit.HEARTS,eRank.THREE,0));
		Pair.add(new Card(eSuit.DIAMONDS,eRank.TWO,0));
		
		Hand h = new Hand();
		h = SetHand(Pair,h);
		
		try {
			h = Hand.EvaluateHand(h);
		} catch (HandException e) {			
			e.printStackTrace();
			fail("TestPair failed");
		}
		HandScore hs = h.getHandScore();
		boolean bActualIsPair = Hand.isHandPair(h, hs);
		boolean bExpectedIsHandPair = true;
		
		assertEquals(bActualIsPair,bExpectedIsHandPair);		
		assertEquals(hs.getHiHand(),eRank.FIVE.getiRankNbr());		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.SPADES);		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.FOUR);
		assertEquals(hs.getKickers().get(eCardNo.SecondCard.getCardNo()).geteSuit(), eSuit.HEARTS);		
		assertEquals(hs.getKickers().get(eCardNo.SecondCard.getCardNo()).geteRank(), eRank.THREE);
		assertEquals(hs.getKickers().get(eCardNo.ThirdCard.getCardNo()).geteSuit(), eSuit.DIAMONDS);		
		assertEquals(hs.getKickers().get(eCardNo.ThirdCard.getCardNo()).geteRank(), eRank.TWO);
	}	
	
	@Test
	public void TestHighCard() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> HighCard = new ArrayList<Card>();
		HighCard.add(new Card(eSuit.CLUBS,eRank.TEN,0));
		HighCard.add(new Card(eSuit.CLUBS,eRank.NINE,0));
		HighCard.add(new Card(eSuit.SPADES,eRank.EIGHT,0));		
		HighCard.add(new Card(eSuit.HEARTS,eRank.SEVEN,0));
		HighCard.add(new Card(eSuit.DIAMONDS,eRank.SIX,0));
		
		Hand h = new Hand();
		h = SetHand(HighCard,h);
		
		boolean bActualIsHandHighCard = Hand.isHandHighCard(h, hs);
		boolean bExpectedIsHandHighCard = true;
		
		assertEquals(bActualIsHandHighCard,bExpectedIsHandHighCard);		
		assertEquals(hs.getHiHand(),eRank.TEN.getiRankNbr());		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.CLUBS);		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.NINE);
		assertEquals(hs.getKickers().get(eCardNo.SecondCard.getCardNo()).geteSuit(), eSuit.SPADES);		
		assertEquals(hs.getKickers().get(eCardNo.SecondCard.getCardNo()).geteRank(), eRank.EIGHT);
		assertEquals(hs.getKickers().get(eCardNo.ThirdCard.getCardNo()).geteSuit(), eSuit.HEARTS);		
		assertEquals(hs.getKickers().get(eCardNo.ThirdCard.getCardNo()).geteRank(), eRank.SEVEN);
		assertEquals(hs.getKickers().get(eCardNo.FourthCard.getCardNo()).geteSuit(), eSuit.DIAMONDS);		
		assertEquals(hs.getKickers().get(eCardNo.FourthCard.getCardNo()).geteRank(), eRank.SIX);
		
	}
	
	@Test
	public void TestHighCardEval() {
		
		ArrayList<Card> HighCard = new ArrayList<Card>();
		HighCard.add(new Card(eSuit.CLUBS,eRank.TEN,0));
		HighCard.add(new Card(eSuit.CLUBS,eRank.NINE,0));
		HighCard.add(new Card(eSuit.SPADES,eRank.EIGHT,0));		
		HighCard.add(new Card(eSuit.HEARTS,eRank.SEVEN,0));
		HighCard.add(new Card(eSuit.DIAMONDS,eRank.SIX,0));
		
		Hand h = new Hand();
		h = SetHand(HighCard,h);
		
		try {
			h = Hand.EvaluateHand(h);
		} catch (HandException e) {			
			e.printStackTrace();
			fail("TestHighCard failed");
		}
		HandScore hs = h.getHandScore();
		boolean bActualIsHandHighCard = Hand.isHandHighCard(h, hs);
		boolean bExpectedIsHandHighCard = true;
		
		assertEquals(bActualIsHandHighCard,bExpectedIsHandHighCard);		
		assertEquals(hs.getHiHand(),eRank.TEN.getiRankNbr());		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.CLUBS);		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.NINE);
		assertEquals(hs.getKickers().get(eCardNo.SecondCard.getCardNo()).geteSuit(), eSuit.SPADES);		
		assertEquals(hs.getKickers().get(eCardNo.SecondCard.getCardNo()).geteRank(), eRank.EIGHT);
		assertEquals(hs.getKickers().get(eCardNo.ThirdCard.getCardNo()).geteSuit(), eSuit.HEARTS);		
		assertEquals(hs.getKickers().get(eCardNo.ThirdCard.getCardNo()).geteRank(), eRank.SEVEN);
		assertEquals(hs.getKickers().get(eCardNo.FourthCard.getCardNo()).geteSuit(), eSuit.DIAMONDS);		
		assertEquals(hs.getKickers().get(eCardNo.FourthCard.getCardNo()).geteRank(), eRank.SIX);
	}		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
