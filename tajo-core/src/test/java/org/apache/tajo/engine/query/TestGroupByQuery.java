/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.tajo.engine.query;

import org.apache.tajo.IntegrationTest;
import org.apache.tajo.QueryTestCaseBase;
import org.apache.tajo.TajoConstants;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.sql.ResultSet;

@Category(IntegrationTest.class)
public class TestGroupByQuery extends QueryTestCaseBase {

  public TestGroupByQuery() {
    super(TajoConstants.DEFAULT_DATABASE_NAME);
  }

  @Test
  public final void testGroupBy() throws Exception {
    // select count(1) as unique_key from lineitem;
    ResultSet res = executeQuery();
    assertResultSet(res);
    cleanupQuery(res);
  }

  @Test
  public final void testGroupBy2() throws Exception {
    // select count(1) as unique_key from lineitem group by l_linenumber;
    ResultSet res = executeQuery();
    assertResultSet(res);
    cleanupQuery(res);
  }

  @Test
  public final void testGroupBy3() throws Exception {
    // select l_orderkey as gkey from lineitem group by gkey order by gkey;
    ResultSet res = executeQuery();
    assertResultSet(res);
    cleanupQuery(res);
  }

  @Test
  public final void testGroupBy4() throws Exception {
    // select l_orderkey as gkey, count(1) as unique_key from lineitem group by lineitem.l_orderkey;
    ResultSet res = executeQuery();
    assertResultSet(res);
    cleanupQuery(res);
  }

  @Test
  public final void testGroupBy5() throws Exception {
    // select l_orderkey as gkey, '00' as num from lineitem group by lineitem.l_orderkey order by gkey
    ResultSet res = executeQuery();
    assertResultSet(res);
    cleanupQuery(res);
  }

  @Test
  public final void testGroupByNested1() throws Exception {
    // select l_orderkey + l_partkey as unique_key from lineitem group by l_orderkey + l_partkey;
    ResultSet res = executeQuery();
    assertResultSet(res);
    cleanupQuery(res);
  }

  @Test
  public final void testGroupByNested2() throws Exception {
    // select sum(l_orderkey) + sum(l_partkey) as total from lineitem group by l_orderkey + l_partkey;
    ResultSet res = executeQuery();
    assertResultSet(res);
    cleanupQuery(res);
  }

  @Test
  public final void testGroupByWithSameExprs1() throws Exception {
    // select sum(l_orderkey) + sum(l_orderkey) as total from lineitem group by l_orderkey + l_partkey;
    ResultSet res = executeQuery();
    assertResultSet(res);
    cleanupQuery(res);
  }

  @Test
  public final void testGroupByWithSameExprs2() throws Exception {
    // select sum(l_orderkey) as total1, sum(l_orderkey) as total2 from lineitem group by l_orderkey + l_partkey;
    ResultSet res = executeQuery();
    assertResultSet(res);
    cleanupQuery(res);
  }

  @Test
  public final void testGroupByWithExpressionKeys1() throws Exception {
    // select upper(lower(l_orderkey::text)) as key, count(1) as total from lineitem
    // group by key order by upper(lower(l_orderkey::text)), total;
    ResultSet res = executeQuery();
    assertResultSet(res);
    cleanupQuery(res);
  }

  @Test
  public final void testGroupByWithExpressionKeys2() throws Exception {
    // select upper(lower(l_orderkey::text)) as key, count(1) as total from lineitem
    // group by upper(lower(l_orderkey::text)) order by upper(l_orderkey::text), total;
    ResultSet res = executeQuery();
    assertResultSet(res);
    cleanupQuery(res);
  }

  @Test
  public final void testGroupByWithSameConstantKeys1() throws Exception {
    // select l_partkey as a, '##' as b, '##' as c, count(*) d from lineitem group by a, b, c;
    ResultSet res = executeQuery();
    assertResultSet(res);
    cleanupQuery(res);
  }

  @Test
  public final void testDistinctAggregation1() throws Exception {
    // select l_orderkey, max(l_orderkey) as maximum, count(distinct l_linenumber) as unique_key from lineitem
    // group by l_orderkey;
    ResultSet res = executeQuery();
    assertResultSet(res);
    cleanupQuery(res);
  }

  @Test
  /**
   * This is an unit test for a combination of aggregation and distinct aggregation functions.
   */
  public final void testDistinctAggregation2() throws Exception {
    // select l_orderkey, count(*) as cnt, count(distinct l_linenumber) as unique_key from lineitem group by l_orderkey;
    ResultSet res = executeQuery();
    assertResultSet(res);
    cleanupQuery(res);
  }

  @Test
  public final void testDistinctAggregation3() throws Exception {
    // select count(*), count(distinct l_orderkey), sum(distinct l_orderkey) from lineitem;
    ResultSet res = executeQuery();
    assertResultSet(res);
    cleanupQuery(res);
  }

  @Test
  public final void testDistinctAggregation4() throws Exception {
    // select l_linenumber, count(*), count(distinct l_orderkey), sum(distinct l_orderkey)
    // from lineitem group by l_linenumber;
    ResultSet res = executeQuery();
    assertResultSet(res);
    cleanupQuery(res);
  }

  @Test
  public final void testDistinctAggregation5() throws Exception {
    // select sum(distinct l_orderkey), l_linenumber, count(distinct l_orderkey), count(*) as total
    // from lineitem group by l_linenumber;
    ResultSet res = executeQuery();
    assertResultSet(res);
    cleanupQuery(res);
  }

  @Test
  public final void testDistinctAggregation6() throws Exception {
    // select count(distinct l_orderkey) v0, sum(l_orderkey) v1, sum(l_linenumber) v2, count(*) as v4 from lineitem
    // group by l_orderkey;
    ResultSet res = executeQuery();
    assertResultSet(res);
    cleanupQuery(res);
  }

  @Test
  public final void testDistinctAggregation7() throws Exception {
    // select count(*), count(distinct c_nationkey), count(distinct c_mktsegment) from customer
    // tpch scale 1000: 15000000	25	5
    ResultSet res = executeQuery();
    assertResultSet(res);
    cleanupQuery(res);
  }

  @Test
  public final void testDistinctAggregationWithHaving1() throws Exception {
    // select l_linenumber, count(*), count(distinct l_orderkey), sum(distinct l_orderkey) from lineitem
    // group by l_linenumber having sum(distinct l_orderkey) >= 6;
    ResultSet res = executeQuery();
    assertResultSet(res);
    cleanupQuery(res);
  }

  @Test
  public final void testDistinctAggregationWithUnion1() throws Exception {
    // select sum(distinct l_orderkey), l_linenumber, count(distinct l_orderkey), count(*) as total
    // from (select * from lineitem union select * from lineitem) group by l_linenumber;
    ResultSet res = executeQuery();
    assertResultSet(res);
    cleanupQuery(res);
  }

  @Test
  public final void testDistinctAggregationCasebyCase() throws Exception {
    ResultSet res;

    // one groupby, distinct, aggregation
    res = executeFile("testDistinctAggregation_case1.sql");
    assertResultSet(res, "testDistinctAggregation_case1.result");
    res.close();

    // one groupby, two distinct, one aggregation
    res = executeFile("testDistinctAggregation_case2.sql");
    assertResultSet(res, "testDistinctAggregation_case2.result");
    res.close();

    // one groupby, two distinct, two aggregation(no alias)
    res = executeFile("testDistinctAggregation_case3.sql");
    assertResultSet(res, "testDistinctAggregation_case3.result");
    res.close();

    // two groupby, two distinct, two aggregation
    res = executeFile("testDistinctAggregation_case4.sql");
    assertResultSet(res, "testDistinctAggregation_case4.result");
    res.close();

    // two groupby, two distinct, two aggregation with subquery
    res = executeFile("testDistinctAggregation_case5.sql");
    assertResultSet(res, "testDistinctAggregation_case5.result");
    res.close();

    res = executeFile("testDistinctAggregation_case6.sql");
    assertResultSet(res, "testDistinctAggregation_case6.result");
    res.close();

    res = executeFile("testDistinctAggregation_case7.sql");
    assertResultSet(res, "testDistinctAggregation_case7.result");
    res.close();

    res = executeFile("testDistinctAggregation_case8.sql");
    assertResultSet(res, "testDistinctAggregation_case8.result");
    res.close();
  }

  @Test
  public final void testComplexParameter() throws Exception {
    // select sum(l_extendedprice*l_discount) as revenue from lineitem;
    ResultSet res = executeQuery();
    assertResultSet(res);
    cleanupQuery(res);
  }

  @Test
  public final void testComplexParameterWithSubQuery() throws Exception {
    ResultSet res = executeQuery();
    assertResultSet(res);
    cleanupQuery(res);
  }

  @Test
  public final void testComplexParameter2() throws Exception {
    // select count(*) + max(l_orderkey) as merged from lineitem;
    ResultSet res = executeQuery();
    assertResultSet(res);
    cleanupQuery(res);
  }

  @Test
  public final void testHavingWithNamedTarget() throws Exception {
    // select l_orderkey, avg(l_partkey) total, sum(l_linenumber) as num from lineitem group by l_orderkey
    // having total >= 2 or num = 3;
    ResultSet res = executeQuery();
    assertResultSet(res);
    cleanupQuery(res);
  }

  @Test
  public final void testHavingWithAggFunction() throws Exception {
    // select l_orderkey, avg(l_partkey) total, sum(l_linenumber) as num from lineitem group by l_orderkey
    // having avg(l_partkey) = 2.5 or num = 1;
    ResultSet res = executeQuery();
    assertResultSet(res);
    cleanupQuery(res);
  }

  @Test
  public final void testGroupbyWithJson() throws Exception {
    // select l_orderkey, avg(l_partkey) total, sum(l_linenumber) as num from lineitem group by l_orderkey
    // having total >= 2 or num = 3;
    ResultSet res = executeJsonQuery();
    assertResultSet(res);
    cleanupQuery(res);
  }
}
