package test

import main.Graph
import org.junit.Before
import org.junit.Test

internal class GraphSearchTest {

    val graph = Graph(10)

    @Before
    fun init() {
        //graph의 연관관계 지어주기

        /**
         * 그래프 형태
            ( 6 )
              |
            ( 8 )  —- ( 1 ) —- ( 2 )
              |   \    /         /
            ( 9 )  ( 3 )     ( 0 ) —- ( 4 )
            /                          /
          (5)                        (7)
         *
         */

        graph.addEdge(6, 8)
        graph.addEdge(8, 1)
        graph.addEdge(8, 9)
        graph.addEdge(8, 3)
        graph.addEdge(9, 5)
        graph.addEdge(3, 8)
        graph.addEdge(3, 1)
        graph.addEdge(1, 8)
        graph.addEdge(1, 3)
        graph.addEdge(1, 2)
        graph.addEdge(2, 0)
        graph.addEdge(2, 1)
        graph.addEdge(0, 2)
        graph.addEdge(0, 4)
        graph.addEdge(4, 0)
        graph.addEdge(4, 7)
        graph.addEdge(7, 4)
    }

    @Test
    fun dfsSearchTest() {
        graph.dfsSearch(6)
    }

}