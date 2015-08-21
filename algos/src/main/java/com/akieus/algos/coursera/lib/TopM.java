package com.akieus.algos.coursera.lib; /******************************************************************************
 *  Compilation:  javac com.akieus.algos.coursera.lib.TopM.java
 *  Execution:    java com.akieus.algos.coursera.lib.TopM M < input.txt
 *  Dependencies: com.akieus.algos.coursera.lib.MinPQ.java com.akieus.algos.coursera.lib.Transaction.java com.akieus.algos.coursera.lib.StdIn.java com.akieus.algos.coursera.lib.StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/24pq/tinyBatch.txt
 * 
 *  Given an integer M from the command line and an input stream where
 *  each line contains a String and a long value, this com.akieus.algos.coursera.lib.MinPQ client
 *  prints the M lines whose numbers are the highest.
 * 
 *  % java com.akieus.algos.coursera.lib.TopM 5 < tinyBatch.txt
 *  Thompson    2/27/2000  4747.08
 *  vonNeumann  2/12/1994  4732.35
 *  vonNeumann  1/11/1999  4409.74
 *  Hoare       8/18/1992  4381.21
 *  vonNeumann  3/26/2002  4121.85
 *
 ******************************************************************************/

/**
 *  The <tt>com.akieus.algos.coursera.lib.TopM</tt> class provides a client that reads a sequence of
 *  transactions from standard input and prints the <em>M</em> largest ones
 *  to standard output. This implementation uses a {@link MinPQ} of size
 *  at most <em>M</em> + 1 to identify the <em>M</em> largest transactions
 *  and a {@link Stack} to output them in the proper order.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/24pq">Section 2.4</a>
 *  of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class TopM {   

    // This class should not be instantiated.
    private TopM() { }

    /**
     *  Reads a sequence of transactions from standard input; takes a
     *  command-line integer M; prints to standard output the M largest
     *  transactions in descending order.
     */
    public static void main(String[] args) {
        int M = Integer.parseInt(args[0]); 
        MinPQ<Transaction> pq = new MinPQ<Transaction>(M+1); 

        while (StdIn.hasNextLine()) {
            // Create an entry from the next line and put on the PQ. 
            String line = StdIn.readLine();
            Transaction transaction = new Transaction(line);
            pq.insert(transaction); 

            // remove minimum if M+1 entries on the PQ
            if (pq.size() > M) 
                pq.delMin();
        }   // top M entries are on the PQ

        // print entries on PQ in reverse order
        Stack<Transaction> stack = new Stack<Transaction>();
        for (Transaction transaction : pq)
            stack.push(transaction);
        for (Transaction transaction : stack)
            StdOut.println(transaction);
    } 
} 
