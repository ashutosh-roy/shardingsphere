/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.infra.aware;

import lombok.Getter;
import org.apache.shardingsphere.spi.ShardingSphereServiceLoader;

import java.util.Optional;

/**
 * Data source name aware factory.
 */
@Getter
public final class DataSourceNameAwareFactory {
    
    private static volatile DataSourceNameAwareFactory instance;
    
    private final DataSourceNameAware dataSourceNameAware;
    
    private DataSourceNameAwareFactory() {
        dataSourceNameAware = ShardingSphereServiceLoader.newServiceInstances(DataSourceNameAware.class).stream().findFirst().orElse(null);
    }
    
    /**
     * Get data source name aware.
     * 
     * @return data source name aware
     */
    public Optional<DataSourceNameAware> getDataSourceNameAware() {
        return Optional.ofNullable(dataSourceNameAware);
    }
    
    /**
     * Get instance.
     *
     * @return instance
     */
    public static DataSourceNameAwareFactory getInstance() {
        if (null == instance) {
            synchronized (DataSourceNameAwareFactory.class) {
                if (null == instance) {
                    instance = new DataSourceNameAwareFactory();
                }
            }
        }
        return instance;
    }
}
