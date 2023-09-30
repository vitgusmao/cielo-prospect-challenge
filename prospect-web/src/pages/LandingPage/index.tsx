import styled from 'styled-components';

import Layout from '../../facades/Layout';

import Content from './Content';
import Footer from './Footer';
import Header from './Header';

const StyledLayout = styled(Layout)`
  height: 100%;
  display: flex;
  flex-direction: column;
`;

function LandingPage() {
  return (
    <StyledLayout>
      <Header />
      <Content />
      <Footer />
    </StyledLayout>
  );
}

export default LandingPage;
