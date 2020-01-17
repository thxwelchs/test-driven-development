package test

import main.Graph
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

internal class GraphSearchTest {

        private val graph = Graph(11)

        @Before
        fun init() {
                /**
                 * DFS 대상 그래프 형태
                ( 0 )
                |
                ( 1 )  —- ( 5 ) —- ( 6 )
                |   \    /         /
                ( 2 )  ( 4 )     ( 7 ) —- ( 8 )
                /                          /
                (3)                        (9)
                 *
                 */
//                graph.addEdge(0, 1)
//                graph.addEdge(1, 5)
//                graph.addEdge(1, 4)
//                graph.addEdge(1, 2)
//                graph.addEdge(2, 3)
//                graph.addEdge(4, 5)
//                graph.addEdge(5, 6)
//                graph.addEdge(6, 7)
//                graph.addEdge(7, 6)
//                graph.addEdge(7, 8)
//                graph.addEdge(8, 9)


                /**
                *   BFS 대상 그래프 형태
                       (9)
                        | |
                       /   \
                     /\    /\
                  (7)(4) (0) (2)
                  / \  \   |  \ \
                 (3)(6)(5)(1)(10)(8)
                *
                */
                graph.addEdge(9, 7)
                graph.addEdge(9, 4)
                graph.addEdge(9, 0)
                graph.addEdge(9, 2)
                graph.addEdge(7, 3)
                graph.addEdge(7, 6)
                graph.addEdge(4, 5)
                graph.addEdge(0, 1)
                graph.addEdge(2, 10)
                graph.addEdge(2, 8)
        }

        @Test
        fun bfsTest() {
                // ROOT Node가 9번이므로 9번부터 출발
                graph.BFS(9)
                // 방문 순서 확인
                assertEquals(graph.visitedNodes.toString(), "[9, 7, 4, 0, 2, 3, 6, 5, 1, 10, 8]")
        }

        @Test
        fun dfsTest() {
                graph.DFS(0)
                // 방문 순서 확인
                assertEquals(graph.visitedNodes.toString(), "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]")
        }

        @Test
        fun dfsRecursionTest() {
                graph.DFSRecursion(graph.nodes[0])
                // 방문 순서 확인
                assertEquals(graph.visitedNodes.toString(), "[0, 1, 5, 4, 6, 7, 8, 9, 2, 3]")
        }
}