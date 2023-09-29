import { Outlet } from 'react-router-dom';
import styled from 'styled-components';

import colors, { hexToRgbText } from '../../colors';
import Layout from '../../facades/Layout';

const StyledContent = styled(Layout.Content)`
  &.page-content {
    flex: 2;
    background-color: ${hexToRgbText(colors.secondaryColor, 17)};
    padding: 20px;
    display: flex;
    justify-content: center;

    transition: font-size 0.3s ease;

    @media screen and (max-width: 400px) {
      padding-inline: 4px;
    }

    @media screen and (min-width: 400px) and (max-width: 768px) {
      padding-inline: 8px;
    }

    .content-container {
      flex: 1;
      @media screen and (min-width: 768px) and (max-width: 1024px) {
        max-width: 456px;
      }

      @media screen and (min-width: 1024px) {
        max-width: 920px;
      }
    }
  }
`;

function Content() {
  return (
    <StyledContent className="page-content">
      <div
        className="content-container"
        aria-label="container do conteúdo do site"
        role="feed"
      >
        <Outlet />
      </div>
    </StyledContent>
  );
}

export default Content;
