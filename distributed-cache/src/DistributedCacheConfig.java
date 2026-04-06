public final class DistributedCacheConfig {
    private final int nodeCount;
    private final int nodeCapacity;

    private DistributedCacheConfig(int nodeCount, int nodeCapacity) {
        if (nodeCount <= 0) {
            throw new IllegalArgumentException("nodeCount must be > 0");
        }
        if (nodeCapacity <= 0) {
            throw new IllegalArgumentException("nodeCapacity must be > 0");
        }
        this.nodeCount = nodeCount;
        this.nodeCapacity = nodeCapacity;
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public int getNodeCapacity() {
        return nodeCapacity;
    }

    public static final class Builder {
        private int nodeCount;
        private int nodeCapacity;

        public Builder nodeCount(int nodeCount) {
            this.nodeCount = nodeCount;
            return this;
        }

        public Builder nodeCapacity(int nodeCapacity) {
            this.nodeCapacity = nodeCapacity;
            return this;
        }

        public DistributedCacheConfig build() {
            return new DistributedCacheConfig(nodeCount, nodeCapacity);
        }
    }
}
