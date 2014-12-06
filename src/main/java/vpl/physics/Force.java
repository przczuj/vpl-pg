	

    /*
     * To change this template, choose Tools | Templates
     * and open the template in the editor.
     */
    package vpl.physics;
     
    import vpl.math.Triple;
    import lombok.Getter;
    import lombok.Setter;
     
    /**
     *
     * @author kppx
     */
    public class Force {
     
        @Setter
        private Triple forceValue;
        @Getter
        @Setter
        private Triple forceLocation;
        @Getter
        @Setter
        private double timeToLive;
        @Getter
        @Setter
        private boolean forever;
     
        public Force() {
            forceValue = new Triple();
            forceLocation = new Triple();
            timeToLive = 1;
            forever = true;
        }
     
        public Triple getForceValue() {
            if (timeToLive <= 0 && !forever) {
                return new Triple();
            }
            return forceValue;
        }
     
        public void decrementTimeToLive(double timeTick, RigidBody rb) {
            if (!forever) {
                timeToLive = timeToLive - timeTick;
            }
            if (timeToLive < 0 && !forever) {
     
                rb.setForcesChanged(true);
            }
        }
    }

